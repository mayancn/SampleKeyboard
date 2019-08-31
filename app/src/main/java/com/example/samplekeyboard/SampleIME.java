package com.example.samplekeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;

import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;


public class SampleIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    KeyboardView keyboardView;
    Keyboard keyboard;
    boolean caps;

    @Override
    public View onCreateInputView() {
        super.onCreateInputView();
        keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.marathi);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }
    @Override
    public void onPress(int primaryCode) {
    }
    @Override
    public void onRelease(int primaryCode) {
    }
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                if(caps){
                    caps = !caps;
                    keyboard = new Keyboard(this, R.xml.marathi_shift);
                    keyboardView.setKeyboard(keyboard);
                    keyboardView.setOnKeyboardActionListener(this);
                }else{
                    caps = !caps;
                    keyboard = new Keyboard(this, R.xml.marathi);
                    keyboardView.setKeyboard(keyboard);
                    keyboardView.setOnKeyboardActionListener(this);
                }
                break;
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode;
                if (Character.isLetter(code) && caps) {
                    code = Character.toUpperCase(code);
                }
                inputConnection.commitText(String.valueOf(code), 1);
        }
    }
    @Override
    public void onText(CharSequence text) {
    }
    @Override
    public void swipeLeft() {
    }
    @Override
    public void swipeRight() {
    }
    @Override
    public void swipeDown() {
    }
    @Override
    public void swipeUp() {
    }
    // AudioManager class is used to play the sound when user pressed the keys.
}
