## Issue

Upgrading Kotlin from 1.6.10 to 1.6.20-RC2 brings runtime crash on a specific context.

It has been tested on a JS/IR library, executed via `ts-node`.

## Context

A class annotated with `@JsExport` that uses `internal lateinit var`.
Fill this field with an implementation in the init block.

KT 1.6.10: works as expected

KT 1.6.20-RC2: crash with
```
TypeError: Cannot read properties of undefined (reading 'someData_1')
```

## Reproducer

A reproducer can be found in this repository. To reproduce, you just need to run `./buildAndRun.sh` script.


## Code

(just a copy-paste from the relevant parts)

[main.kt](src/jsMain/kotlin/testing/main.kt) :
```kotlin
class NonExportedClass { val someData = 12 }

@JsExport
class Foo {
    internal lateinit var nec: NonExportedClass

    init {
        nec = NonExportedClass()
    }

    val exposedValue: Int get() = nec.someData
}
```

[main.ts](src/jsMain/kotlin/testing/main.ts) :
```typescript
var t = new testing.Foo()
//KT 1.6.10: print "12"
//KT 1.6.20-RC2: TypeError: Cannot read properties of undefined (reading 'someData_1')
console.log(t.exposedValue)
```