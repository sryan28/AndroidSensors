package com.user.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/*
Majority of devices have accelerometer and gyroscope
Functions: Tilt, shake, rotation, swing

Monitoring motion relative to the devices frame of reference(In moving car)
OR relative to the worlds frame of reference

All motion sensors return array of sensor values in a SensorEvent object

Accelerometer returns force data for x, y, z axis: units m/s^2
Gyroscope returns rate of rotation for x, y, z axis: units rad/s
Returned as floats

Linear Acceleration: Acceleration without gravity
Acceleration: Acceleration with gravity
Gyroscope: Rate of Rotation
Gyroscope Uncalibrated: Rate of rotation without drift compensation
Gravity: Force of gravity
Rotation Vector: Rotation vector components

 */


public class MainActivity extends AppCompatActivity {

    //linear acceleration
    private SensorManager linearAccelSensorManager;
    private Sensor linearaccelSensor;

    //acceleration
    private SensorManager accelSensorManager;
    private Sensor accelSensor;

    //gravity
    private SensorManager gravitySensorManager;
    private Sensor gravitySensor;

    private TextView xLinAccel;
    private TextView yLinAccel;
    private TextView zLinAccel;

    private TextView xAccel;
    private TextView yAccel;
    private TextView zAccel;

    private TextView xGravity;
    private TextView yGravity;
    private TextView zGravity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xLinAccel = (TextView) findViewById(R.id.x_force);
        yLinAccel = (TextView) findViewById(R.id.y_force);
        zLinAccel = (TextView) findViewById(R.id.z_force);

        xAccel = (TextView) findViewById(R.id.x_accel_gravity);
        yAccel = (TextView) findViewById(R.id.y_accel_gravity);
        zAccel = (TextView) findViewById(R.id.z_accel_gravity);

        xGravity = (TextView) findViewById(R.id.x_gravity);
        yGravity = (TextView) findViewById(R.id.y_gravity);
        zGravity = (TextView) findViewById(R.id.z_gravity);


        //Linear acceleration sensor(excludes gravity)
        linearAccelSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        linearaccelSensor = linearAccelSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        linearAccelSensorManager.registerListener(linearAccelListener, linearaccelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Acceleration sensor(includes gravity)
        accelSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelSensor = accelSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelSensorManager.registerListener(accelListener, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Gravity sensor
        gravitySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravitySensor = gravitySensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        gravitySensorManager.registerListener(gravityListener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    SensorEventListener linearAccelListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linearAccelValues = event.values;

            xLinAccel.setText("X linear acceleration : " + String.valueOf(linearAccelValues[0]));
            yLinAccel.setText("Y linear acceleration : " + String.valueOf(linearAccelValues[1]));
            zLinAccel.setText("Z linear acceleration : " + String.valueOf(linearAccelValues[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener accelListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] accelerationValues = event.values;

            xAccel.setText("X acceleration : " + String.valueOf(accelerationValues[0]));
            yAccel.setText("Y acceleration : " + String.valueOf(accelerationValues[1]));
            zAccel.setText("Z acceleration : " + String.valueOf(accelerationValues[2]));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener gravityListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] gravityValues = event.values;

            xGravity.setText("X gravity : " + String.valueOf(gravityValues[0]));
            yGravity.setText("Y gravity : " + String.valueOf(gravityValues[1]));
            zGravity.setText("Z gravity : " + String.valueOf(gravityValues[2]));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
