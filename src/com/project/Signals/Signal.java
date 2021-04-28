package com.project.Signals;

import com.birdbraintechnologies.Finch;
import com.project.GUISystem;

/* Singleton pattern */

public class Signal{
    // instance
    protected static volatile Signal _instance;

    // thread
    protected static volatile Thread _signalThread = new Thread();

    protected Signal(){}

    // start point method
    public final synchronized void play(Finch targetFinch){
        if(!_signalThread.isAlive()) {
            _signalThread = new Thread((Runnable) ()->{
                controlPlaying(targetFinch);
            }, "Signal Thread");
            _signalThread.start();
        }
    }

    public static final boolean isPlaying(){
        return _signalThread.isAlive();
    }

    // methods for custom override
    protected void manipulating(Finch targetFinch){
        System.out.println("Please, override the method");
    }

    // method running "manipulating()"
    private void controlPlaying(Finch targetFinch){
        try{
            manipulating(targetFinch);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

