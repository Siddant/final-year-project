package fintessapplication.com.siddant.fintessapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    ListView listSavedFiles;
    String[] SavedFiles;
    ArrayList<String> SavedtxtFiles = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listSavedFiles = (ListView)findViewById(R.id.list);
        ShowSavedFiles();
        listSavedFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = new Intent();
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                try {
                    String file = ((TextView) view).getText().toString();
                    display_records.file = file;
                    intent.setClass(history.this, display_records.class);
                    startActivity(intent);
                } catch (Exception e) {
                }

            }
        });
    }
    void ShowSavedFiles(){

        // SavedFiles = getApplicationContext().fileList();
        SavedFiles = getApplicationContext().fileList();
        boolean needtoadd = false;
        for(int i=0; i<SavedFiles.length; i++){
            String file = SavedFiles[i];
            if(Character.isDigit(file.charAt(0)) == true) {

                SavedtxtFiles.add(file);
            }


        }


        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                SavedtxtFiles);

        listSavedFiles.setAdapter(adapter);
    }


}
