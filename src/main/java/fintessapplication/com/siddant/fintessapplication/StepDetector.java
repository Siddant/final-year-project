package fintessapplication.com.siddant.fintessapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
//�߲�����������ڼ���߲�������

/**
 * �����㷨��̫��������㷨�Ǵӹȸ�Ʋ�����Pedometer�Ͻ�ȡ�Ĳ��ּƲ��㷨
 * 
 */
public class StepDetector implements SensorEventListener {

	public static int CURRENT_SETP = 0;
	public static int CURRENT_SETP_30secs = 0;
	public static int CURRENT_SETP_1mins = 0;
	public static int CURRENT_SETP_1secs = 0;
	public static boolean isrunning;
	public static int mCounterSteps = 0;
	public static int mCounterSteps30secs = 0;
	public static int mCounterSteps1mins = 0;
	public static int mCounterSteps1secs = 0;

	boolean activityRunning = true;

	String type = "1";

	public StepDetector(Context context) {
		// TODO Auto-generated constructor stub
		super();

	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;

			if(activityRunning) {
			if(sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
					if (mCounterSteps < 1) {
						mCounterSteps = (int) event.values[0];
					}
						if (isrunning == true) {
							CURRENT_SETP = (int) event.values[0] - mCounterSteps;
							CURRENT_SETP_30secs = (int) event.values[0] - (mCounterSteps + mCounterSteps30secs);
							CURRENT_SETP_1mins = (int) event.values[0] - (mCounterSteps + mCounterSteps1mins);
						}
						CURRENT_SETP_1secs = (int) event.values[0] - (mCounterSteps + mCounterSteps1secs);
					}

			}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}



}
