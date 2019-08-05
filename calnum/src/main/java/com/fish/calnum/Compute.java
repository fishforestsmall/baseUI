package com.fish.calnum;

public class Compute {
    static {
        System.loadLibrary("calnum");
    }

    public static native float add(float a, float b);
}
