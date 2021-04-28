package com.project;

public final class Timer {
    private Thread _timerThread = new Thread();

    public Timer(){}

    public void run(int millis){
        if(!_timerThread.isAlive()) {
            _timerThread = new Thread((Runnable) () -> {
                startThread(millis);
            }, "Timer Thread");
            _timerThread.start();
        }
    }

    public boolean isWorks(){
        return _timerThread.isAlive();
    }

    private void startThread(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
