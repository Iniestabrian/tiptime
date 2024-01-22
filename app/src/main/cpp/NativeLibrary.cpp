#include <jni.h>
/*
extern "C" {
    JNIEXPORT jstring JNICALL
    Java_com_example_tiptime_jni_NativeClass_getBaseUrl(JNIEnv *env, jobject *//* this *//*) {

    }
}*/

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_tiptime_jni_NativeClass_00024Companion_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://newsapi.org/v2/");
}