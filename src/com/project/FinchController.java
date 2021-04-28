package com.project;

import com.birdbraintechnologies.Finch;
import com.project.Signals.DangerSignal;
import com.project.Signals.Signal;
import com.project.Signals.SignalOfLimit;

import javax.swing.*;

public class FinchController extends JFrame {
    // finch
    private Finch _finch;

    // thread
    private Thread _moveThread;

    // listener
    protected KeyboardHandler _listener;

    // states
    private boolean _isControlled = false;
    private boolean _isMobile = false;

    // sensors data
    protected SensorsHandler _sensorsHandler;

    // properties of finch
    protected int _speed = 255;          // range: 0 - 255
    protected int _direction = 1;        // back: -1, stop: 0, forward: 1
    protected float _rightWheelWork = 1; // range: 0 - 1
    protected float _leftWheelWork = 1;  // range: 0 - 1

    // constants
    protected final float ROTATION = 0.4f;
    protected final int MAX_SPEED = 255;
    protected final int MIN_SPEED = 0;
    protected final int DELTA_SPEED = 1;
    protected final int TIME_TO_HOLD_MOVE = 10;

    public FinchController(Finch finch){
        _finch = finch;
    }

    // start point method running "moveFinch()"
    public final void startControl(){
        if(!_isControlled) {
            _isControlled = true;

            _moveThread = new Thread(this::moveFinch, "Move Thread");
            _moveThread.start();
        }
    }

    // stop point method staying "moveFinch()"
    public final void stopControl(boolean byForce) {
        if(_isControlled)
            if (byForce) {
                _isControlled = false;
            } else {
                try {
                    _moveThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    // methods of linking
    public final void setSensorsHandler(SensorsHandler sensorsHandler){
        _sensorsHandler = sensorsHandler;
    }
    public final void setListener(KeyboardHandler listener){
        _listener = listener;
    }

    public final boolean isControlled(){
        return _isControlled;
    }

    // methods updating properties and setting rules of processing this ones
    protected void updateDirection(){
        if(_isControlled) {
            if (_listener.isUp && !_listener.isDown && !_sensorsHandler.isObstacle()) {
                _direction = 1;
            } else {
                if(_sensorsHandler.isObstacle()){
                    Signal signal = DangerSignal.getInstance();
                    signal.play(_finch);
                }
                if (_listener.isDown && !_listener.isUp) {
                    _direction = -1;
                } else {
                    _direction = 0;
                }
            }
        }
    }
    protected void updateRotate(){
        if(_isControlled) {
            if (_listener.isRight && !_listener.isLeft) {
                _leftWheelWork = 1;
                _rightWheelWork = !_listener.isShift ? ROTATION : 0;
            } else if (_listener.isLeft && !_listener.isRight) {
                _leftWheelWork = !_listener.isShift ? ROTATION : 0;
                _rightWheelWork = 1;
            } else {
                _rightWheelWork = 1;
                _leftWheelWork = 1;
            }
        }
    }
    protected void updateSpeed(){
        if(_isControlled && !Signal.isPlaying()) {
            if ((_listener.isPlus || _listener.isEquals) && !(_listener.isMinus || _listener.isUnderscope)) {
                if(_speed == MAX_SPEED){
                    Signal signal = SignalOfLimit.getInstance();
                    signal.play(_finch);
                } else {
                    _speed += _speed + DELTA_SPEED <= MAX_SPEED ? DELTA_SPEED : MAX_SPEED - _speed;
                }
            } else if ((_listener.isMinus || _listener.isUnderscope) && !(_listener.isPlus || _listener.isEquals)) {
                if(_speed == MIN_SPEED){
                    Signal signal = SignalOfLimit.getInstance();
                    signal.play(_finch);
                } else {
                    _speed -= _speed - DELTA_SPEED >= MIN_SPEED ? DELTA_SPEED : _speed;
                }
            }
        }
    }

    private void moveFinch() {
        if(!_isMobile) {
            _isMobile = true;

            while (_isControlled && !_listener.isEscape) {
                updateDirection();
                updateRotate();
                updateSpeed();

                correctProperties();

                _finch.setWheelVelocities((int) (_direction * _leftWheelWork * _speed), (int) (_direction * _rightWheelWork * _speed), TIME_TO_HOLD_MOVE);
            }
            _finch.setWheelVelocities(0,0);

            _isMobile = false;
        }
    }

    // method of correct range of properties
    private void correctProperties(){
        _speed = _speed < 0 ? 0 : _speed > 255 ? 255 : _speed;
        _direction = _direction < -1 ? -1 : _direction > 1 ? 1 : _direction;
        _rightWheelWork = _rightWheelWork < 0 ? 0 : _rightWheelWork > 1 ? 1 : _rightWheelWork;
        _leftWheelWork = _leftWheelWork < 0 ? 0 : _leftWheelWork > 1 ? 1 : _leftWheelWork;
    }
}
