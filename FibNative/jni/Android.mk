LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := FibLib
LOCAL_SRC_FILES := FibLib.cpp
LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)
