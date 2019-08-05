//
// Created by Administrator on 2019/8/2.
//

#include "com_fish_calnum_Compute.h"

JNIEXPORT jfloat JNICALL Java_com_fish_calnum_Compute_add
  (JNIEnv *env, jclass j, jfloat a, jfloat b) {
    return a+ b;
  }