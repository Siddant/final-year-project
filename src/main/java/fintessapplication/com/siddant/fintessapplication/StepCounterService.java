package fintessapplication.com.siddant.fintessapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;


//service�����̨����Ҫ�������е�����
 // �Ʋ�������
 // �����ں�̨�ķ����������˽��沿�ֵĿ�����
 // �Ϳ��Կ�����̨�ķ�����StepService
 // ע���ע�������������������ֻ���Ļ״̬����ʾ֪ͨ����StepActivity����ͨ�ţ��߹��Ĳ����ǵ������ˣ�����
public class StepCounterService extends Service {

	public static Boolean FLAG = false;// �������б�־

	private SensorManager mSensorManager;// ����������
	private StepDetector detector;// ��������������

	private PowerManager mPowerManager;// ��Դ�������
	private PowerManager.WakeLock mWakeLock;// ��Ļ��
	private static final String TAG = "MyActivity";


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		FLAG = true;// ���Ϊ������������
		Log.i(TAG, String.valueOf(FLAG));

		// �����������࣬ʵ������������
		detector = new StepDetector(this);

		mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		// ע�ᴫ������ע�������
		Sensor countSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

		mSensorManager.registerListener(detector, countSensor, SensorManager.SENSOR_DELAY_FASTEST);
		// ��Դ�������
		mPowerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP, "S");
		mWakeLock.acquire();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		FLAG = false;// ����ֹͣ
		if (detector != null) {
			mSensorManager.unregisterListener(detector);
		}

		if (mWakeLock != null) {
			mWakeLock.release();
		}
	}

}
