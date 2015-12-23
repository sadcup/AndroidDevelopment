//
// Created by Netiger on 12/23/15.
//

#include "JNIStringDemo.h"

JNIStringDemo::JNIStringDemo(const char *str) {
    data = str;
}

string JNIStringDemo::substr(int begin, int end) {
    return data.substr(begin, end);
}