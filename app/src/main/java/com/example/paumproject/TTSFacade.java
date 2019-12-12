package com.example.paumproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Set;

public class TTSFacade {
    private Context appContext;
    private TextToSpeech tts;
    private TextAssets textAssets;

    public TTSFacade(Context context) {
        appContext = context;
        textAssets = new TextAssets();
    }

    public void speak(final String text) {
        tts = new TextToSpeech(appContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    if (tts.isLanguageAvailable(Locale.getDefault()) < tts.LANG_AVAILABLE) {
                        Toast.makeText(appContext, textAssets.LANGUAGE_UNAVAILABLE, Toast.LENGTH_LONG).show();
                    } else {
                        tts.setLanguage(new Locale.Builder().setLanguage("pl").setRegion("PL").build());
                        tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
                    }
                }
            }
        });
    }
}
