package com.example.paumproject;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalibrationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        final TTSFacade tts = new TTSFacade(getApplicationContext());
        tts.speak("Calibration. Please place your fingers on the screen in order, starting from your thumb.");

        final List<Coordinates> coordinates = new ArrayList();

        ConstraintLayout layout = findViewById(R.id.calibration_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && event.getPointerCount() == 5) {
                    for (int i = 0; i < 5; i++) {
                        coordinates.add(new Coordinates(event.getX(i), event.getY(i)));
                    }

                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        v.vibrate(100);
                    }
                    tts.speak("Calibration complete");

                    Intent intent = new Intent(getApplicationContext(), KeyboardActivity.class);
                    intent.putExtra("coordinates", (Serializable)coordinates);
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });
    }
}
