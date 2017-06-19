package fintessapplication.com.siddant.fintessapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText sharedsteplength,textMax,textMin;
    private Button save,cancel;
    Spinner spinner2,spinner,spinner3;
    public static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String STEP_LENGTH_VALUE = "step_length_value";// ����
    public static final String TARGET_PACE = "step_Pace_value";// ����
    public static final String TARGET_SPEED = "step_Speed_value";// ����
    public static final String SET = "step_SET_value";// ����
    public static final String SETP_SHARED_PREFERENCES = "setp_shared_preferences";// ����
    public static final String option = "every second";// ����
    public static final String option1 = "lol second";// ����
    public static final String max = "MAximum Speed!!";// ����
    public static final String min = "minimum Speed!!";// ����
    public static final String Pacemax = "MAximum Pace!!";// ����
    public static final String Pacemin = "minimum Pace!!";// ����
    public static final String maintainspeed = "maintain Pace!!";// ����

    private float number =0;
    private float number1 =0;
    private float number3 =0;

    private float maximum =0;
    private Double maximumPace;
    private float minimum =0;
    private Double minimumPace;
    private float stride_length = 0;

    private float  targetspeed= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        addView();
        init();

    }



    private void init() {
        // TODO Auto-generated method stub

        if (sharedPreferences == null) {    //SharedPreferences��Androidƽ̨��һ���������Ĵ洢�࣬
            //��Ҫ�Ǳ���һЩ���õ����ñ��細��״̬
            sharedPreferences = getSharedPreferences(SETP_SHARED_PREFERENCES,
                    MODE_PRIVATE);
        }

        editor = sharedPreferences.edit();
        stride_length =sharedPreferences.getFloat(STEP_LENGTH_VALUE, 0);
        targetspeed= sharedPreferences.getFloat(TARGET_SPEED, 0);
        number =sharedPreferences.getFloat(option, 0);
        number1=sharedPreferences.getFloat(option1, 0);
        number3=sharedPreferences.getFloat(maintainspeed, 0);
        maximum =sharedPreferences.getFloat(max, 0);
        maximumPace = Double.valueOf(sharedPreferences.getFloat(Pacemax, 0));
        minimum =sharedPreferences.getFloat(min, 0);
        minimumPace = Double.valueOf(sharedPreferences.getFloat(Pacemin, 0));
        sharedsteplength.setText("" + stride_length);
        textMax.setText("" + maximum);
        textMin.setText("" + minimum);
        spinner2.setSelection((int) number);
        spinner.setSelection((int)number1);
        spinner3.setSelection((int)number3);
    }


    private void addView() {
        sharedsteplength = (EditText) this.findViewById(R.id.editText2);
        textMax = (EditText) this.findViewById(R.id.editText);
        textMin = (EditText) this.findViewById(R.id.editText4);
        save = (Button)this.findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        cancel = (Button)this.findViewById(R.id.button4);
        cancel.setOnClickListener(this);


        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.maintain, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);




// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.revive, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.option, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

        init();
    }

    @Override
         protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        init();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if(Float.parseFloat(textMax.getText().toString())<=Float.parseFloat(textMin.getText().toString())) {
                    alertView("Your minimum speed is larger then your maximum speed");
                }else if(Float.parseFloat(sharedsteplength.getText().toString())<=0){
                    alertView("invalid stride length");
                }else{
                    maximumPace = 60/Double.parseDouble(textMax.getText().toString());
                    int pacemin = maximumPace.intValue();
                    double pacesecs = Math.round(((maximumPace - pacemin) * 0.60) * 100.0) / 100.0;
                    maximumPace = pacemin+pacesecs;
                    minimumPace = 60/Double.parseDouble(textMin.getText().toString());
                    int pacemin1 = minimumPace.intValue();
                    double pacesecs1 = Math.round(((minimumPace - pacemin1) * 0.60) * 100.0) / 100.0;
                    minimumPace = pacemin1+pacesecs1;
                    stride_length = Float.parseFloat(sharedsteplength.getText().toString());

                    editor.putFloat(STEP_LENGTH_VALUE, stride_length);
                    editor.putFloat(max, Float.parseFloat(textMax.getText().toString()));
                    editor.putFloat(Pacemax, Float.parseFloat(String.valueOf(maximumPace)));
                    editor.putFloat(min, Float.parseFloat(textMin.getText().toString()));
                    editor.putFloat(Pacemin, Float.parseFloat(String.valueOf(minimumPace)));
                    editor.putFloat(option, number);
                    editor.putFloat(option1, number1);
                    editor.putFloat(maintainspeed, number3);
                    editor.commit();
                    Toast.makeText(settings.this, "data has been saved", Toast.LENGTH_SHORT).show();
                    this.finish();
                }

                break;

            case R.id.button4:
                this.finish();
                break;


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextSize(20);

        switch (parent.getId()) {
            case R.id.spinner2:
                number = position;
                break;
            case R.id.spinner:
                number1 = position;
                break;
            case R.id.spinner3:
                number3 = position;
                break;

        }


    }
    private void alertView( String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle( "Alert" )
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(message)
//  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//      public void onClick(DialogInterface dialoginterface, int i) {
//          dialoginterface.cancel();
//          }})
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
