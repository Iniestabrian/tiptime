cmake_minimum_required(VERSION 3.10.2)

project(TipTimeNativeLibrary)

add_library(NativeLibrary SHARED NativeLibrary.cpp)

set_target_properties(NativeLibrary PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}")

