package net.omisoft.track;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalNativeKeyListenerExample implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Keyboard pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

}