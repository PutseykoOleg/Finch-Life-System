package com.project.Signals;

import com.birdbraintechnologies.Finch;

public final class DangerSignal extends Signal {
    private DangerSignal() {}

    public static DangerSignal getInstance(){
        Signal instance = _instance;
        if(instance != null){
            return (DangerSignal) instance;
        }
        synchronized (Signal.class){
            if(_instance == null){
                _instance = new DangerSignal();
            }
            return (DangerSignal) _instance;
        }
    }

    @Override
    protected synchronized void manipulating(Finch targetFinch) {
        try {
            targetFinch.saySomething("Danger");
            targetFinch.setLED(50, 0, 0, 500);
            Thread.sleep(500);
            targetFinch.saySomething("Danger");
            targetFinch.setLED(0, 50, 200, 500);
            Thread.sleep(500);
            targetFinch.saySomething("Opasnost'");
            targetFinch.setLED(0, 50, 200, 500);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
