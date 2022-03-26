import { testing } from '@kjt/testing'

var t = new testing.Foo()
console.log(t) // print "Foo { nec_1: NonExportedClass { someData_1: 12 } }"

//KT 1.6.10: print "12"
//KT 1.6.20-RC2: TypeError: Cannot read properties of undefined (reading 'someData_1')
console.log(t.exposedValue)
