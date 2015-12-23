//
// Created by Netiger on 12/23/15.
//

#include "JNIDemo.h"
#include <stdio.h>
#include <stdlib.h>

void JNIDemo::print() {
    printf("JNIDemo from JNA.\n");
}

JNIDemo::JNIDemo() {
    num = 0;
}

JNIDemo::JNIDemo(int a) {
    num = a;
}

int JNIDemo::add(int b) {
    return num + b;
}
