@file:OptIn(ExperimentalJsExport::class)

package testing

val dynamicNull: dynamic = null

class NonExported {
    val goo = 12
}

@JsExport
class Foo(data1: String, data2: String){
    internal lateinit var common: NonExported

    init {
        if (data1 != dynamicNull) {
            common = NonExported()
        }
    }

    val exposedValue: Int
        get() = common.goo

    internal constructor(common: NonExported): this(dynamicNull?.dynamicCastTo<String>(), dynamicNull?.dynamicCastTo<String>()) {
        this.common = common
    }
}

@JsExport
fun buildTestingKT() = Foo(NonExported()) // internal constructor

