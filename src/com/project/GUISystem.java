package com.project;

import com.birdbraintechnologies.Finch;

import javax.swing.*;

/* Singleton pattern */

public class GUISystem extends JFrame {
    // instance
    private static volatile GUISystem _instance;

    // finch elements
    private volatile Finch _finch;
    private FinchLifeSystem _lifeSystem;

    // listener
    protected final KeyboardHandler _listener = new KeyboardHandler();

    // swing elements
    private JFrame _frame = new JFrame();

    // state
    private boolean _isLaunched = false;

    private GUISystem(Finch finch, String finchName){
        _finch = finch;
        _lifeSystem = new FinchLifeSystem(_finch, finchName);

        linkWithListener();
    }

    public static GUISystem getInstance(Finch finch, String finchName){
        GUISystem instance = _instance;
        if(instance != null){
            return instance;
        }
        synchronized (GUISystem.class){
            if(_instance == null){
                _instance = new GUISystem(finch, finchName);
            }
            return _instance;
        }
    }

    public void launch(){
        createWindow();
        _lifeSystem.run();
        _lifeSystem.setMoveControl(true);

        _isLaunched = true;

        while(_isLaunched){}
    }

    private void linkWithListener(){
        _frame.addKeyListener(_listener);
        _lifeSystem.addListener(_listener);
    }

    private void createWindow(){
        _frame.setSize(500, 500);
        _frame.setVisible(true);
    }

}
