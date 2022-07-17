//
// Created by handi on 12/27/19.
//

#include <jni.h>
#include <string>
#include <iostream>


extern "C" JNIEXPORT jstring JNICALL
Java_com_ebayk_network_AuthInterceptor_getUsername(JNIEnv *env, jclass clazz) {
    std::string username = "candidate";
    return env->NewStringUTF(username.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_ebayk_network_AuthInterceptor_getPassword(JNIEnv *env, jclass clazz) {
    std::string password = "DELETED";
    return env->NewStringUTF(password.c_str());
}
