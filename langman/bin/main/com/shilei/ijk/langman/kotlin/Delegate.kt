package com.shilei.ijk.langman.kotlin

import kotlin.properties.Delegates

// 延迟属性 Lazy properties
val lazyValue: String by lazy {
    println("-------------begin------------")
    println("computed!")
    "Hello"
}

// 可观察属性 Observable properties
class User {
    var name: String by Delegates.observable("张三") { prop, old, new ->
        println("$old -> $new")
    }

    var eag: String by Delegates.vetoable("18") { prop, old, new ->
        println("$old -> $new")
        true
    }
}

// 委托给另一个属性
var topLevelInt: Int = 0
class ClassWithDelegate(val anotherClassInt: Int)
class MyClass(private var memberInt: Int, private val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt
    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}
var MyClass.extDelegated: Int by ::topLevelInt

// Use Case
class MyClass2 {
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName
}

// 将属性储存在映射中
class User2(map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

// 局部委托属性
class Foo {
    fun isValid(): Boolean {
        return true
    }

    fun doSomething() {
        println("Congratulations!")
    }

}

fun localPropertyDelegate(computeFoo: () -> Foo) {
    val someCondition = false
    val memoizedFoo by lazy(computeFoo)

    // memoizedFoo 变量只会在第一次访问时计算。 如果 someCondition 失败，那么该变量根本不会计算。
    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}


