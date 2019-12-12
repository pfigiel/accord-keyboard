package com.example.paumproject;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KeyboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        List<Coordinates> coordinates = (List<Coordinates>)extras.get("coordinates");
        ConstraintLayout layout = findViewById(R.id.keyboard_layout);
        List<Drawable> drawables = getCircleDrawables();
        final TTSFacade tts = new TTSFacade(getApplicationContext());
        final TouchProcessor touchProcessor = new TouchProcessor(coordinates);
        final ITouchDecoder decoder = (ITouchDecoder)new AlphabeticalTouchDecoder();
        final TextView input = findViewById(R.id.inputTextView);

        for (int i = 0; i < coordinates.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());

            imageView.setImageDrawable(drawables.get(i));
            imageView.setX(coordinates.get(i).X - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
            imageView.setY(coordinates.get(i).Y - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));

            layout.addView(imageView);
        }







        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                TouchProcessor.Status status = touchProcessor.processTouch(event);

                switch (status) {
                    case PROCESSED:
                        TouchAction action = decoder.decodeTouch(touchProcessor.getFingerIndexes());
                        if (action.getType() != TouchAction.ActionType.READ_ALL) {
                            tts.speak(action.getTextToRead());
                        }
                        if (action.getType() == TouchAction.ActionType.CHARACTER) {
                            input.setText(input.getText() + String.valueOf(action.getCharacter()));
                        } else if (action.getType() == TouchAction.ActionType.SPACE) {
                            input.setText(input.getText() + " ");
                        } else if (action.getType() == TouchAction.ActionType.BACKSPACE && input.getText().length() > 0) {
                            input.setText(input.getText().subSequence(0, input.getText().length() - 1));
                        } else if (action.getType() == TouchAction.ActionType.READ_ALL) {
                            tts.speak((String)input.getText());
                        }
                        break;

                }

                return true;
            }
        });


    }

    private List<Drawable> getCircleDrawables() {
        List<Drawable> drawables = new ArrayList();

        drawables.add(getResources().getDrawable(R.drawable.thumb_circle));
        drawables.add(getResources().getDrawable(R.drawable.index_finger_circle));
        drawables.add(getResources().getDrawable(R.drawable.middle_finger_circle));
        drawables.add(getResources().getDrawable(R.drawable.ring_finger_circle));
        drawables.add(getResources().getDrawable(R.drawable.little_finger_circle));

        return drawables;
    }
}
