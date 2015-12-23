//
// Created by Netiger on 12/23/15.
//

#ifndef JNA_ON_AS_JNIDEMOINTERFACE_H
#define JNA_ON_AS_JNIDEMOINTERFACE_H


extern "C" void * initialize(int a);
extern "C" void   run(void *);
extern "C" void * finalize(void *);
extern "C" int    add(void * demo, int b);


extern "C" void * initializeString(char * str);
extern "C" const char * substrString(void * demoString, int begin, int end);
extern "C" void finalizeString(void * demoString);

#endif //JNA_ON_AS_JNIDEMOINTERFACE_H
