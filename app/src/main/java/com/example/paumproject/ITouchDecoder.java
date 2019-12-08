package com.example.paumproject;

import java.util.List;

public interface ITouchDecoder {
    public TouchAction decodeTouch(List<Integer> fingerIndexes);
}
