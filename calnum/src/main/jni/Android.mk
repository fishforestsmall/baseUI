LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := calnum
LOCAL_SRC_FILES := com_fish_calnum_Compute.c

include $(BUILD_SHARED_LIBRARY)