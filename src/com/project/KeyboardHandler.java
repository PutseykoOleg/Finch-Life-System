package com.project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardHandler extends KeyAdapter {
    public boolean isUp = false;
    public boolean isRight = false;
    public boolean isDown = false;
    public boolean isLeft = false;

    public boolean isEscape = false;
    public boolean isShift = false;

    public boolean isPlus = false;
    public boolean isEquals = false;
    public boolean isMinus = false;
    public boolean isUnderscope = false;

    public int keyCode = -1;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (keyCode = e.getKeyCode()){
            case KeyEvent.VK_UP:
                isUp = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                break;
            case KeyEvent.VK_DOWN:
                isDown = true;
                break;
            case KeyEvent.VK_LEFT:
                isLeft = true;
                break;
            case KeyEvent.VK_ESCAPE:
                isEscape = true;
                break;
            case KeyEvent.VK_SHIFT:
                isShift = true;
                break;
            case KeyEvent.VK_PLUS:
                isPlus = true;
                break;
            case KeyEvent.VK_EQUALS:
                isEquals = true;
                break;
            case KeyEvent.VK_MINUS:
                isMinus = true;
                break;
            case KeyEvent.VK_UNDERSCORE:
                isUnderscope = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (keyCode = e.getKeyCode()){
            case KeyEvent.VK_UP:
                isUp = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = false;
                break;
            case KeyEvent.VK_DOWN:
                isDown = false;
                break;
            case KeyEvent.VK_LEFT:
                isLeft = false;
                break;
            case KeyEvent.VK_ESCAPE:
                isEscape = false;
                break;
            case KeyEvent.VK_SHIFT:
                isShift = false;
                break;
            case KeyEvent.VK_PLUS:
                isPlus = false;
                break;
            case KeyEvent.VK_EQUALS:
                isEquals = false;
                break;
            case KeyEvent.VK_MINUS:
                isMinus = false;
                break;
            case KeyEvent.VK_UNDERSCORE:
                isUnderscope = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keyPressed(e);
        //keyReleased(e);
    }
}
