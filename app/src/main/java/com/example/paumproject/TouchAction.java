package com.example.paumproject;

public class TouchAction {
    public enum ActionType {
        CHARACTER, SPACE, BACKSPACE, READ_ALL, ERROR
    }

    private ActionType type;
    private String textToRead;
    private Character character;

    public TouchAction(ActionType type, String textToRead, Character character) {
        this.type = type;
        this.textToRead = textToRead;
        this.character = character;
    }

    public ActionType getType() {
        return type;
    }

    public String getTextToRead() {
        return type == ActionType.CHARACTER ? String.valueOf(character) : textToRead;
    }

    public Character getCharacter() {
        return character;
    }
}
