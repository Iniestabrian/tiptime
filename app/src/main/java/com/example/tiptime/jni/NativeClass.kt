package com.example.tiptime.jni

class NativeClass {
    companion object {
        init {
            System.loadLibrary("NativeLibrary")
        }

        external fun getBaseUrl(): String
    }
}