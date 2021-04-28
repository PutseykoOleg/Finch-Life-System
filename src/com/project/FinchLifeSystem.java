package com.project;

import com.birdbraintechnologies.Finch;

import java.io.File;

public final class FinchLifeSystem {
    // constituents of the system
    private Finch _finch;
    private FinchController _controller;
    private SensorsHandler _sensorsHandler;

    // data of finch's life
    private LifeIndicators _lifeIndicators;
    private FinchPersonalData _finchPersonalData;

    public FinchLifeSystem(Finch finch, String finchName){
        _finch = finch;

        _controller = new FinchController(_finch);
        _sensorsHandler = new SensorsHandler(_finch);
        _finchPersonalData = new FinchPersonalData(finchName);

        linkConstituents();
    }

    private void linkConstituents(){
        _controller.setSensorsHandler(_sensorsHandler);
    }

    public void addListener(KeyboardHandler listener){
        _controller.setListener(listener);
    }

    public void run(){
        _sensorsHandler.startProcessing();

        //loadData();
    }
    public void stop(){
        _sensorsHandler.stopProcessing(false);
    }

    public void setMoveControl(boolean control){
        if(control && !_controller.isControlled()){
            _controller.startControl();
        } else if(_controller.isControlled()){
            _controller.stopControl(true);
        }
    }

    private void loadLifeIndicators(){
        String pathToSave = LifeIndicators.getPathToSave(_finchPersonalData.getName());

        File saveFile = new File(pathToSave);
        if(saveFile.exists()){
            _lifeIndicators = (LifeIndicators) SaveManager.uploadObjects(pathToSave).get(0);
        } else {
            _lifeIndicators = new LifeIndicators();
        }
    }
}
