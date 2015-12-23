#include "sadcup_android_jnionas_JNIKit.h"

JNIEXPORT jint JNICALL Java_sadcup_android_jnionas_JNIKit_square
        (JNIEnv * env, jclass obj, jint num) {
    return num * num;
}

JNIEXPORT jstring JNICALL Java_sadcup_android_jnionas_JNIKit_welcome
        (JNIEnv * env, jclass obj) {
    return (env)->NewStringUTF("I am from JNI");
}