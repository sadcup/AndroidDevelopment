//
// Created by Netiger on 12/23/15.
//

#ifndef JNA_ON_AS_JNISTRINGDEMO_H
#define JNA_ON_AS_JNISTRINGDEMO_H

#include <string>
using namespace std;

class JNIStringDemo {
    string data;
public:
    JNIStringDemo(const char *);
    string substr(int begin, int end);
};


#endif //JNA_ON_AS_JNISTRINGDEMO_H

