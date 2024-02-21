package com.shilei.ijk.okretro.anno

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class AuthToken(val needAuthToken: Boolean = true)
