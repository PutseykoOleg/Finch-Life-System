package com.project;

import com.birdbraintechnologies.Finch;

import java.sql.Time;

public class SensorsHandler {
    // finch
    private Finch _finch;

    // state
    private boolean _inProcessing = false;

    // thread
    private Thread _sensorsThread = new Thread();

    // data
    private boolean _isObstacleRightSide = false;
    private boolean _isObstacleLeftSide = false;
    private double _temperature = 0;
    private int _rightLightSensorData = 0;
    private int _leftLightSensorData = 0;
    private double[] _accelerations = {0, 0, 0};

    // constants
    private int UPDATE_FREQUENCY_MILLIS = 40;

    SensorsHandler(Finch finch){
        _finch = finch;
    }

    public void startProcessing(){
        if(!_sensorsThread.isAlive()) {
            _sensorsThread = new Thread(this::trackSensorsData, "Sensors Thread");
            _sensorsThread.start();
        }
    }
    public void stopProcessing(boolean byForce) {
        if(_sensorsThread.isAlive())
            if(byForce) {
                _inProcessing = false;
            } else {
                try {
                    _sensorsThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    private void trackSensorsData(){
        Timer timer = new Timer();

        while (_inProcessing) {
            if(!timer.isWorks()) {
                _isObstacleRightSide = _finch.isObstacleRightSide();
                _isObstacleLeftSide = _finch.isObstacleLeftSide();
                //_temperature = _finch.getTemperature();
                //_rightLightSensorData = _finch.getRightLightSensor();
                //_leftLightSensorData = _finch.getLeftLightSensor();
                //_accelerations = _finch.getAccelerations();

                timer.run(UPDATE_FREQUENCY_MILLIS);
            }
        }
    }

    public boolean isObstacleRightSide(){
        return _isObstacleRightSide;
    }
    public boolean isObstacleLeftSide(){
        return _isObstacleLeftSide;
    }
    public boolean isObstacle(){
        return _isObstacleRightSide || _isObstacleLeftSide;
    }
    public double getTemperature(){
        return _temperature;
    }
    public double getRightLightSensorData(){
        return _rightLightSensorData;
    }
    public double getLeftLightSensorData(){
        return _leftLightSensorData;
    }
    public double getLightSensorsData(){
        return (_rightLightSensorData + _leftLightSensorData) / 2;
    }
    // [x, y, z]
    public double[] getAccelerations(){
        return _accelerations;
    }

    public boolean inProcessing(){
        return _sensorsThread.isAlive();
    }
}
