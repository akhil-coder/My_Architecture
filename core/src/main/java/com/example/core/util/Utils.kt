package com.example.core.util

val <T>T.exhaustive: T
    get() = this

fun <K,V>HashMap<K, V>.getKey(value: V) : K {
    return this.keys.first { value == this[it] }
}