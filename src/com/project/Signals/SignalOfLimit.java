package com.project.Signals;

import com.birdbraintechnologies.Finch;

public final class SignalOfLimit extends Signal {
    private SignalOfLimit() {}

    public static SignalOfLimit getInstance(){
        Signal instance = _instance;
        if(instance != null){
            return (SignalOfLimit) instance;
        }
        synchronized (Signal.class){
            if(_instance == null){
                _instance = new SignalOfLimit();
            }
            return (SignalOfLimit) _instance;
        }
    }

    @Override
    protected synchronized void manipulating(Finch targetFinch) {
        try {
            targetFinch.saySomething("Peep");
            targetFinch.setLED(0, 50, 200, 500);
            Thread.sleep(500);
            targetFinch.saySomething("Peep");
            targetFinch.setLED(0, 50, 200, 500);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
