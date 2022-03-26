@file:OptIn(ExperimentalJsExport::class)

package testing

class NonExportedClass { val someData = 12 }

@JsExport
class Foo {
    internal lateinit var nec: NonExportedClass

    init {
        nec = NonExportedClass()
    }

    val exposedValue: Int get() = nec.someData
}
