package fintessapplication.com.siddant.fintessapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
@SuppressLint("HandlerLeak")

public class View_Activity extends AppCompatActivity implements View.OnClickListener{
    public static int steps30secs=0;
    public static int steps1secs=0;
    public static int steps1mins=0;
    public static int total_step=0;
    Vibrator mVibrator;
    TextToSpeech t1;

    public int x=0;
    long thour;
    long tminutes;
    long tsecond;
    long hour;
    long minutes;
    long second;
    private TextView timer, distance, Steps, speed, pace;
    private Button btnstart, btnpause;
    private Double Distance = 0.0;
    long[] once = { 0, 700 };
    long[] twice = { 0, 700, 300, 700 };
    long[] thrice = { 0, 700, 300, 700, 300, 700 };
    int long_gap = 1000;
    private Double Speed = 0.0;
    private Double Pace = 0.0;
    static double step_length = 0;
    private int stoptimer = 0;
    private int countdownstoptimer = 60;
    MediaPlayer player,Stopper,player2;
    private float number =0;
    private float maxSpeed,minSpeed,number1,maxpace,minpace,maintain;
    private int targettime =0;
    private int needtoadd =0;
    private int addingtime =0;
    private int settext =0;
    private  long startTimer = 0;
    private long Timer = 0;
    private long Timerwasted = 0;
    private  long tempTime = 0;
    private long millis;

    ArrayList<String> Timearray = new ArrayList<String>();
    ArrayList<String> speedarray = new ArrayList<String>();
    ArrayList<String> pacearray = new ArrayList<String>();

    private boolean	added =false;
    private boolean	activity ;
    private boolean	paused;
    private boolean	start;

    final CounterClass countdown = new CounterClass(600000, 10);

    private Thread thread;

    Handler handler;

    {
        handler = new Handler() {

            @Override                  //��������ǴӸ���/�ӿ� �̳й����ģ���Ҫ��дһ��
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);        // �˴����Ը���UI



               if (second == stoptimer) {


                   if(StepCounterService.FLAG == true && steps1secs <1){
                       if(minutes ==0&& second==0){
                      if (number == 1) {
                           beep(1);
                       } else if (number == 0) {
                            vibrate(4);
                       } else if (number == 2) {
                          String toSpeak = "Start";
                            //voice(toSpeak);

                       }
                       }


                       activity = false;
                        paused = true;
                        countdown.start();
                        StepDetector.isrunning = false;

                    }
                 else if(steps1secs ==0){
                        activity = false;
                        paused = true;
                        countdown.start();
                        StepDetector.isrunning = false;


                    } else {
                        StepDetector.isrunning = true;
                        activity = true;

                    }

                if(paused == true){
                    startTimer = System.currentTimeMillis();
                    tempTime = Timer;
                    paused = false;
                }

                    if (stoptimer != 60) {
                        stoptimer = stoptimer + 5;
                    } else {
                        stoptimer = 0;
                    }

                    StepDetector.mCounterSteps1secs = total_step;
                    StepDetector.CURRENT_SETP_1secs = 0;
                    steps1secs =0;
                }

                if (activity == true) {

                    if (second == targettime && added == false) {
                        if (minutes == addingtime) {
                            Timearray.add(String.valueOf(minutes));
                            speedarray.add(String.valueOf(formatDouble(getSpeed(steps1mins, 120))));
                            pacearray.add(String.valueOf(formatDouble(getPace(steps1mins, 120))));
                            StepDetector.mCounterSteps1mins = total_step;
                            StepDetector.CURRENT_SETP_1mins = 0;
                            steps1mins = 0;
                            total_step =0;
                            addingtime = addingtime + 2;
                        }

                        if (second <= 30) {
                   if(second == 0){
                       if(minutes ==0 && second ==0){

                       }else {

                           if ((getSpeed(steps30secs, 30) < maxSpeed) && (getSpeed(steps30secs, 30) > minSpeed)) {
                               if (number == 1) {
                                   if(maintain ==0) {
                                       beep(2);
                                   }
                               } else if (number == 0) {
                                   vibrate(1);
                               } else if (number == 2) {
                                   if(maintain ==0) {
                                       String toSpeak = "maintain speed";
                                       //voice(toSpeak);
                                   }
                               }
                           } else if ((getSpeed(steps30secs, 30) > maxSpeed)) {
                               if (number == 1) {
                                   beep(3);
                               } else if (number == 0) {
                                   vibrate(2);
                               } else if (number == 2) {
                                   String toSpeak = "slow down";
                                   //voice(toSpeak);
                               }
                           } else {
                               if (number == 1) {
                                   beep(4);
                               } else if (number == 0) {
                                   vibrate(3);
                               } else if (number == 2) {
                                   String toSpeak = "speed up";
                                   //voice(toSpeak);
                               }
                           }
                       }
                            }else {

                       if ((getSpeed(steps30secs, (int) second) < maxSpeed) && (getSpeed(steps30secs, (int) second) > minSpeed)) {


                                   if (number == 1) {
                                       if(maintain ==0) {
                                           beep(2);
                                       }

                                   } else if (number == 0) {
                                       vibrate(1);

                                   } else if (number == 2) {
                                       if(maintain ==0) {
                                           String toSpeak = "maintain speed";
                                           //voice(toSpeak);
                                       }
                                   }

                        } else if ((getSpeed(steps30secs, (int) second) > maxSpeed)) {
                           if (number == 1) {
                               beep(3);


                           } else if (number == 0) {
                               vibrate(2);

                           } else if (number == 2) {
                               String toSpeak = "slow down";
                               //voice(toSpeak);
                           }
                        } else {
                           if (number == 1) {

                               beep(4);
                           } else if (number == 0) {
                               vibrate(3);
                           } else if (number == 2) {
                               String toSpeak = "speed up";
                               //voice(toSpeak);
                           }
                        }
                   }
                        } else {

                                if((getSpeed(steps30secs, ((int) second - 30))  <  maxSpeed) && (getSpeed(steps30secs, ((int) second - 30))  >  minSpeed)) {


                                        if (number == 1) {
                                            if(maintain ==0) {
                                                beep(2);
                                            }

                                        } else if (number == 0) {
                                            vibrate(1);

                                        } else if (number == 2) {
                                            if(maintain ==0) {
                                                String toSpeak = "maintain speed";
                                                //voice(toSpeak);
                                            }

                                        }

                                }else if ((getSpeed(steps30secs, ((int) second - 30))  >  maxSpeed)){
                                    if (number == 1) {
                                        beep(3);


                                    } else if (number == 0) {
                                        vibrate(2);

                                    } else if (number == 2) {
                                        String toSpeak = "slow down";
                                        //voice(toSpeak);
                                    }
                                }else{
                                    if (number == 1) {

                                        beep(4);
                                    } else if (number == 0) {
                                        vibrate(3);
                                    } else if (number == 2) {
                                        String toSpeak = "speed up";
                                        //voice(toSpeak);
                                    }
                                }
                        }


                        added = true;


                    } else if (second == (targettime + 1) && added == true) {
                        added = false;
                        targettime = targettime + needtoadd;

                    } else {
                        if (targettime != 60) {
                        } else {
                            targettime = 0;

                        }
                        if (second == 0 || second == 30) {
                             StepDetector.mCounterSteps30secs = total_step;
                            StepDetector.CURRENT_SETP_30secs = 0;
                            steps30secs = 0;

                        }


                        countStep();
                        distance.setText(formatDouble(countDistance(total_step)) + "");

                        timer.setText(getFormatTime(Timer));

                        if(second == settext) {
                            if (second <= 30) {
                                if (minutes == 0 && second == 0) {

                                } else if(second == 0) {
                                    speed.setText(getSpeed(steps30secs, (int) 30) + "");
                                    pace.setText(getPace(steps30secs, (int) 30) + "");
                                } else {
                                    speed.setText(getSpeed(steps30secs, (int) second) + "");
                                    pace.setText(getPace(steps30secs, (int) second) + "");
                                }

                            } else {
                                speed.setText(getSpeed(steps30secs, ((int) second - 30)) + "");
                                pace.setText(getPace(steps30secs, ((int) second - 30)) + "");

                            }
                            settext = settext +5;
                            if(settext == 60){
                                settext = 0;
                            }
                        }
                    }

                }
            }



        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(StepCounterService.FLAG == false) {
            StepDetector.mCounterSteps = 0;
            StepDetector.CURRENT_SETP_30secs = 0;
            StepDetector.CURRENT_SETP_1secs = 0;
            StepDetector.CURRENT_SETP_1mins = 0;
            StepDetector.CURRENT_SETP = 0;
        }
        /*t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });*/
        setContentView(R.layout.activity_view);


       if (settings.sharedPreferences == null) {
            settings.sharedPreferences = this.getSharedPreferences(
                    settings.SETP_SHARED_PREFERENCES,
                    Context.MODE_PRIVATE);
        }
      if (thread == null) {

            thread = new Thread() {// ���߳����ڼ�����ǰ�����ı仯

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    super.run();					int temp = 0;

                    while (true) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        if (activity == true) {
                            Message msg = new Message();


                            if (temp != StepDetector.CURRENT_SETP) {
                                temp = StepDetector.CURRENT_SETP;
                            }


                            if (startTimer != System.currentTimeMillis()) {
                                Timer = (tempTime + System.currentTimeMillis()
                                        - startTimer)-Timerwasted;

                            }
                            handler.sendMessage(msg);
                        }

                        }
                    }

            };

            thread.start();
        }
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub

        super.onResume();
        addView();


        init();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        Intent service = new Intent(this, StepCounterService.class);

        total_step = 0;
        steps30secs = 0;
        steps1mins = 0;
        steps1secs =0;
        Timearray.clear();
        speedarray.clear();
        pacearray.clear();
        stopService(service);

        super.onDestroy();


    }

    private void addView() {
        player = MediaPlayer.create(this, R.raw.beep);
        player2 = MediaPlayer.create(this, R.raw.qq);

        Stopper = MediaPlayer.create(this, R.raw.buzzer);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        timer  = (TextView) this.findViewById(R.id.timer);
        distance = (TextView) this.findViewById(R.id.distance);
        Steps = (TextView) this.findViewById(R.id.Steps);
        speed = (TextView) this.findViewById(R.id.speed);
        pace = (TextView) this.findViewById(R.id.pace);
        Steps.setText("0");
        distance.setText("0.0");
        speed.setText("0.0");
        pace.setText("0.0");
        timer.setText("00.00.00");
        btnstart = (Button) this.findViewById(R.id.btnStart);
        btnstart.setOnClickListener(this);
        btnpause = (Button) this.findViewById(R.id.btnPause);
        btnpause.setOnClickListener(this);


    }
    private void init() {
        step_length = settings.sharedPreferences.getFloat(settings.STEP_LENGTH_VALUE, 0);
        maxSpeed = settings.sharedPreferences.getFloat(settings.max, 0);
        minSpeed= settings.sharedPreferences.getFloat(settings.min, 0);
        number1 = settings.sharedPreferences.getFloat(settings.option1, 0);
        //targetSpeed = settings.sharedPreferences.getFloat(settings.TARGET_SPEED, 0);
        //targetPace = settings.sharedPreferences.getFloat(settings.TARGET_PACE, 0);
        number = settings.sharedPreferences.getFloat(settings.option, 0);
        maxpace = settings.sharedPreferences.getFloat(settings.Pacemax, 0);
        minpace = settings.sharedPreferences.getFloat(settings.Pacemin, 0);
        maintain = settings.sharedPreferences.getFloat(settings.maintainspeed, 0);
        countStep();
        distance.setText(countDistance(total_step) + "");
        if(StepCounterService.FLAG == false) {
            btnstart.setEnabled(true);
            btnpause.setEnabled(false);
        }else {
            btnstart.setEnabled(false);
            btnpause.setEnabled(true);
        }
        if(number1 == 0){
            needtoadd =10;
        }else if(number1 == 1){
            needtoadd =30;
        }else{
            needtoadd = 0;
        }
    }

    private double countDistance(int step) {
        Distance = (((step / 2) * step_length) /100)/1000;
        return Distance;
    }

    private double getSpeed(int step, int timer) {
        double time  = (double)timer/(double)60;
        if(step == 0 || step_length == 0 || (minutes == 0 && second ==0)){
            Speed = 0.0;
        }else {
            Speed = Math.round(((countDistance(step) / (time)) * 60) * 100.0) / 100.0;
        }
        return Speed;
    }

    private double getPace(int step, int timer) {
        double time  = (double)timer/(double)60;
        if(step == 0 || step_length == 0 || (minutes == 0 && second ==0)){
                Pace = 0.0;
        }else {
            Pace = Math.round(((time) / (countDistance(step))) * 100.0) / 100.0;
            int pacemin = Pace.intValue();
            double pacesecs = Math.round(((Pace - pacemin) * 0.60) * 100.0) / 100.0;
            Pace = pacemin+pacesecs;
        }
        return Pace;
    }




    private void countStep() {
        steps30secs = StepDetector.CURRENT_SETP_30secs;
        steps1mins = StepDetector.CURRENT_SETP_1mins;
        total_step =StepDetector.CURRENT_SETP;
        steps1secs =StepDetector.CURRENT_SETP_1secs;

        Steps.setText((total_step) + "");
        //Steps.setText((steps30secs) + "");

    }

    @Override
    public void onBackPressed() {
    }
    private String formatDouble(Double doubles) {
        DecimalFormat format = new DecimalFormat("####.##");
        String distanceStr = format.format(doubles);
        return distanceStr.equals(getString(R.string.zero)) ? getString(R.string.double_zero)
                : distanceStr;
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                service("start");
                btnstart.setEnabled(false);
                btnpause.setEnabled(true);
                startTimer = System.currentTimeMillis();
                tempTime = Timer;
                activity = true;
                start = true;
                StepDetector.isrunning = true;
                countStep();
                break;


            case R.id.btnPause:
                service("stop");
                handler.removeCallbacks(thread);
                Intent intent = new Intent();
                intent.setClass(View_Activity.this, showData.class);
                intent.putExtra("Timearray", Timearray);
                intent.putExtra("pacearray", pacearray);
                intent.putExtra("speedarray", speedarray);
                showData.distance = Distance;
                showData.duration = getFormatTime(Timer);
                showData.maxspeed = maxSpeed;
                showData.minspeed = minSpeed;
                showData.maxpace = maxpace;
                showData.minpace = minpace;
                activity = false;
                Steps.setText("0");
                distance.setText("0.0");
                speed.setText("0.0");
                pace.setText("0.0");
                startActivity(intent);
                countdown.cancel();

                break;
        }
    }


 public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            millis = millisUntilFinished;

            thour =  TimeUnit.MILLISECONDS.toHours(millis);
            tminutes = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
            tsecond =TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));


          if(StepDetector.CURRENT_SETP == 0) {

           }else{

               timer.setText( thour + ":" +  tminutes + ":" + tsecond);

           }
            countStep();

            if(steps1secs > 0) {
                activity = true;
                countdown.cancel();
                StepDetector.isrunning = true;
                x =0;
                if(hour == 0&&minutes ==0 && second ==0 ) {
                    Timerwasted = (600000) - millis;
                }else{
                    Timerwasted = (600000 + 5000) - millis;

                }

            }


        }




        @Override
        public void onFinish() {
            // TODO Auto-generated method stub

            service("stop");
            handler.removeCallbacks(thread);
            Intent intent = new Intent();
            intent.setClass(View_Activity.this, showData.class);
            intent.putExtra("Timearray", Timearray);
            intent.putExtra("pacearray", pacearray);
            intent.putExtra("speedarray", speedarray);
            showData.distance = Distance;
            showData.duration = getFormatTime(Timer);;
            activity = false;
            countdown.cancel();
            Steps.setText("0");
            distance.setText("0.0");
            speed.setText("0.0");
            pace.setText("0.0");
            startActivity(intent);


        }


    }

private void service(String services){

    Intent service = new Intent(this, StepCounterService.class);
    if(services == "start") {
        startService(service);
    }else {
        stopService(service);
    }


}
    private String getFormatTime(long time) {

        second = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time));
        minutes = TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time));
        hour = TimeUnit.MILLISECONDS.toHours(time);



        String strSecond = ("00" + second)
                .substring(("00" + second).length() - 2);
        String strMinute = ("00" + minutes)
                .substring(("00" + minutes).length() - 2);
        String strHour = ("00" + hour).substring(("00" + hour).length() - 2);

        return( strHour + ":" + strMinute + ":" + strSecond);
    }
    private void beep(int step) {
        if(step == 1){
            Stopper.start();
        } else if(step == 4){
            player2.start();
        }else {
            int x = 0;
            while (x < (step-1)) {
                if (player.isPlaying() == true) {
                } else {
                    player.start();
                    x++;
                }
            }
        }
    }
    private void vibrate(int step) {

        if(step ==1){
            mVibrator.vibrate(once, -1);

        }else if(step ==2){
            mVibrator.vibrate(twice, -1);

        }else if(step ==3){
            mVibrator.vibrate(thrice, -1);

        }else{
            mVibrator.vibrate(long_gap);
        }
    }

    /* private void voice(String step) {
        t1.speak(step, TextToSpeech.QUEUE_FLUSH, null);

    }*/
}
