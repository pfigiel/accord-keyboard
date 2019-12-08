package com.example.paumproject;

import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TouchProcessor {
    public enum Status {
        PROCESSED, IN_PROGRESS, ERROR
    }

    private boolean isUserPlacingFingers = false;
    private boolean isUserLiftingFingers = false;
    private boolean isError = false;
    private int currentlyPlacedFingersCount = 0;
    private List<Coordinates> placedFingersCoordinates;
    private List<Coordinates> referenceCoordinates;
    List<Integer> fingerIndexes;

    public TouchProcessor(List<Coordinates> referenceCoordinates) {
        placedFingersCoordinates = new ArrayList();
        fingerIndexes = new ArrayList();
        this.referenceCoordinates = referenceCoordinates;
    }

    private void clear() {
        isUserPlacingFingers = false;
        isUserLiftingFingers = false;
        placedFingersCoordinates.clear();
        fingerIndexes.clear();
    }

    private boolean isActionHandled(int action) {
        return action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP ||
                action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN;
    }

    // Return true if decoded, false if still in the process
    public Status processTouch(MotionEvent event) {
        if (event.getPointerCount() == 0) {
            isError = false;
            clear();
        }

        int eventAction = event.getAction() & MotionEvent.ACTION_MASK;

        if (isError || !isActionHandled(eventAction)) {
            return Status.IN_PROGRESS;
        }

        // User placed the first finger on screen, set flag and add it to the list
        if (eventAction == MotionEvent.ACTION_DOWN && event.getPointerCount() == 1) {
            currentlyPlacedFingersCount = event.getPointerCount();
            isUserPlacingFingers = true;
            placedFingersCoordinates.add(new Coordinates(event.getX(event.getPointerCount() - 1), event.getY(event.getPointerCount() - 1)));
            return Status.IN_PROGRESS;
        }

        // User placed consecutive finger on screen, add it to the list
        if (eventAction == MotionEvent.ACTION_POINTER_DOWN && isUserPlacingFingers) {
            placedFingersCoordinates.add(new Coordinates(event.getX(event.getPointerCount() - 1), event.getY(event.getPointerCount() - 1)));
            return Status.IN_PROGRESS;
        }


        // User is lifting fingers from the screen, set the flag
        if (eventAction == MotionEvent.ACTION_POINTER_UP) {
            isUserLiftingFingers = true;
            return Status.IN_PROGRESS;
        }

        // User lifted the last finger from the screen
        if (eventAction == MotionEvent.ACTION_UP && (isUserLiftingFingers || placedFingersCoordinates.size() == 1) && event.getPointerCount() == 1) {
            if (this.coordinatesToFingerIndexes()) {
                return Status.PROCESSED;
            } else {
                return Status.ERROR;
            }
        }

        // Anything else is treated as error
        isError = true;
        return Status.ERROR;
    }

    public boolean coordinatesToFingerIndexes() {
        for (int i = 0; i < placedFingersCoordinates.size(); i++) {
            List<Pair<Integer, Float>> fingerIndexesDistances = new ArrayList();

            for (int j = 0; j < referenceCoordinates.size(); j++) {
                fingerIndexesDistances.add(Pair.create(j, (float) Math.sqrt(Math.pow(placedFingersCoordinates.get(i).X - referenceCoordinates.get(j).X, 2) + Math.pow(placedFingersCoordinates.get(i).Y - referenceCoordinates.get(j).Y, 2))));
            }

            fingerIndexesDistances.sort(new Comparator<Pair<Integer, Float>>() {
                @Override
                public int compare(Pair<Integer, Float> pairA,
                                   Pair<Integer, Float> pairB) {
                    return pairA.second.compareTo(pairB.second);
                }
            });

            for (int j = 0; j < fingerIndexesDistances.size(); j++) {
                if (!fingerIndexes.contains(fingerIndexesDistances.get(j).first)) {
                    fingerIndexes.add(fingerIndexesDistances.get(j).first);
                    break;
                }
            }
        }

        return true;
    }

    public List<Integer> getFingerIndexes() {
        List<Integer> indexes = new ArrayList();
        for(Integer index : fingerIndexes) {
            indexes.add(index);
        }
        clear();
        return indexes;
    }
}
