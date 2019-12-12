package com.example.paumproject;

import java.util.Locale;

public class TextAssets {
    private final String EN = "english";
    private final String PL = "polski";

    public String AWAITING_CALIBRATION;
    public String CALIBRATION_INSTRUCTIONS;
    public String CALIBRATION_CONFIRMATION;
    public String LANGUAGE_UNAVAILABLE;
    public String SPACE;
    public String TRY_AGAIN;

    public TextAssets() {
        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case PL:
                AWAITING_CALIBRATION = "Kalibracja...";
                CALIBRATION_INSTRUCTIONS = "Kalibracja. Przyłóż palce do ekranu w kolejności, zaczynając od kciuka";
                CALIBRATION_CONFIRMATION = "Kalibracja zakończona";
                LANGUAGE_UNAVAILABLE = "Język niedostępny. Upewnij się że urządzenie używa Google Text To Speech";
                SPACE = "Spacja";
                TRY_AGAIN = "Spróbuj ponownie";
                break;
            case EN:
            default:
                AWAITING_CALIBRATION = "Awaiting calibration...";
                CALIBRATION_INSTRUCTIONS = "Calibration. Please place your fingers on the screen in order, starting from your thumb.";
                CALIBRATION_CONFIRMATION = "Calibration complete";
                LANGUAGE_UNAVAILABLE = "Language unavailable. Please make sure your device is using Google Text To Speech";
                SPACE = "Space";
                TRY_AGAIN = "Try again";
                break;
        }
    }
}
