# 设置cmake版本要求
cmake_minimum_required(VERSION 3.10.2)


set(CMAKE_LIBRARY_OUTPUT_DIRECTORY src/main/jni/../jniLibs/${ANDROID_ABI})

set(CMAKE_CXX_FLAGS " -frtti -std=gnu++11  -D_CGE_USE_FFMPEG_ -DDEBUG -DANDROID_NDK -DCGE_TEXTURE_PREMULTIPLIED=1 -D__STDC_CONSTANT_MACROS -D_CGE_DISABLE_GLOBALCONTEXT_ -O3 -ffast-math -D_CGE_ONLY_FILTERS_")

ADD_SUBDIRECTORY(src/main/jni/cge)

ADD_SUBDIRECTORY(src/main/jni/source)