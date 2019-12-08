package com.example.paumproject;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TTSFacade {
    private Context appContext;
    private TextToSpeech tts;

    public TTSFacade(Context context) {
        appContext = context;
    }

    public void speak(final String text) {
        tts = new TextToSpeech(appContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
    }
}
