package com.example.paumproject;

import java.util.List;

public class AlphabeticalTouchDecoder implements ITouchDecoder {
    public TouchAction decodeTouch(List<Integer> fingerIndexes) {
        int touchCode = 0;
        for (int i = 0; i < fingerIndexes.size(); i++) {
            touchCode += Math.pow(2, fingerIndexes.get(i));
        }

        switch (touchCode) {
            case 1:             // thumb - backspace
                return new TouchAction(TouchAction.ActionType.BACKSPACE, "Backspace", null);
            case 30:
                return new TouchAction(TouchAction.ActionType.READ_ALL, "Reading text", null);
            case 31:            // all fingers - space
                return new TouchAction(TouchAction.ActionType.SPACE, "Space", null);
            case 2:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'a');
            case 3:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'b');
            case 4:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'c');
            case 5:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'd');
            case 6:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'e');
            case 7:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'f');
            case 8:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'g');
            case 9:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'h');
            case 10:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'i');
            case 11:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'j');
            case 12:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'k');
            case 13:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'l');
            case 14:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'm');
            case 15:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'n');
            case 16:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'o');
            case 17:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'p');
            case 18:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'q');
            case 19:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'r');
            case 20:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 's');
            case 21:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 't');
            case 22:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'u');
            case 23:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'v');
            case 24:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'w');
            case 25:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'x');
            case 26:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'y');
            case 27:
                return new TouchAction(TouchAction.ActionType.CHARACTER, null, 'z');
            default:
                return new TouchAction(TouchAction.ActionType.ERROR, "Please try again", null);
        }
    }
}
