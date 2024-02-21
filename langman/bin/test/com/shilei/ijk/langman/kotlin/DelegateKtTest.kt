package com.shilei.ijk.langman.kotlin

import org.junit.Test

class DelegateKtTest {

    @Test
    fun testLazyValue() {
        println(lazyValue)
        println(lazyValue)
    }

    @Test
    fun testObservableProperties() {
        val user = User()
        user.name = "李四"
        user.eag = "19"
        user.name = "王五"
        user.eag = "20"
    }

    @Test
    fun testDelegateToAnotherProperty() {
        val classWithDelegate = ClassWithDelegate(22)
        val myClass = MyClass(12, classWithDelegate)
        println(myClass.delegatedToMember)
        println(myClass.delegatedToTopLevel)
        println(myClass.delegatedToAnotherClass)
        println(myClass.extDelegated)

        val myClass2 = MyClass2()
        myClass2.newName = 15
        println(myClass2.newName) // 15
        myClass2.oldName = 11
        println(myClass2.oldName) // 11
        println(myClass2.newName) // 11

        // 通知：'oldName: Int' is deprecated.
        // Use 'newName' instead
        myClass2.oldName = 42
        println(myClass2.newName) // 42
    }

    @Test
    fun testPropertyDelegatesInMap() {
        val user = User2(
            mapOf(
                "name" to "John Doe",
                "age" to 25
            )
        )
        println(user.name) // Prints "John Doe"
        println(user.age)  // Prints 25
    }
}