package com.example.harsh.weatherapp.extensions

import java.lang.IllegalStateException
import kotlin.reflect.KProperty

class NotNullSingleValueVar<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
            value ?: throw IllegalStateException("${property.name} not initialized")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, val1: T) {
        this.value = if (this.value == null) val1
                     else throw IllegalStateException("${property.name} already initialized")
    }
}

object DelegatesExtensions {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}