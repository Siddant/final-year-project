package fintessapplication.com.siddant.fintessapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends AppCompatActivity implements View.OnClickListener{
    private Button btnstartactivity, btnhistory, btnsettings;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        btnstartactivity = (Button)this.findViewById(R.id.button);
        btnstartactivity.setOnClickListener(this);

        btnhistory = (Button)this.findViewById(R.id.button2);
        btnhistory.setOnClickListener(this);

        btnsettings = (Button) this.findViewById(R.id.button3);
        btnsettings.setOnClickListener(this);

        init();


    }
    @Override
    public void onBackPressed() {
    }


    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button:
                intent.setClass(menu.this, View_Activity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(menu.this, history.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent.setClass(menu.this, settings.class);
                startActivity(intent);
                break;
        }
    }



    private void init() {
        Intent intent = new Intent();

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);

        if (isFirstRun) {
            Toast.makeText(menu.this, "data has been saved", Toast.LENGTH_SHORT).show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isfirstrun", false).commit();
            intent.setClass(menu.this, settings.class);
            startActivity(intent);

        }
    }

}
