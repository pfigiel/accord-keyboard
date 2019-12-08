package com.example.paumproject;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public float X;
    public float Y;

    public Coordinates(float x, float y) {
        X = x;
        Y = y;
    }
}
