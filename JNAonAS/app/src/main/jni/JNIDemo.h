//
// Created by Netiger on 12/23/15.
//

#ifndef JNA_ON_AS_JNIDEMO_H
#define JNA_ON_AS_JNIDEMO_H
//#include <string>
//using namespace std;

class JNIDemo {
private:
    int num;
public:
    void print();
    JNIDemo();
    JNIDemo(int a);
    int add(int b);
};


#endif //JNA_ON_AS_JNIDEMO_H
