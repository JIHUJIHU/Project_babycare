package com.example.kimminseong.babyhelper;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private CreateDB db;
    String tbName;
    member m;
    int check = 0;
    String id_;
    String pw_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tbName = "MEMBER";

        db = new CreateDB(MainActivity.this,
                tbName,
                null,1);

        db.makeDB();

        final List list = db.get();

        final EditText id = (EditText)findViewById(R.id.idEdit);
        final EditText pw = (EditText)findViewById(R.id.pwEdit);
        Button login_btn = (Button)findViewById(R.id.login_button);
        Button join_btn = (Button)findViewById(R.id.join_button);




        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0 ; i < list.size();i++){
                    id_ = id.getText().toString();
                    pw_ = pw.getText().toString();
                    m = (member) list.get(i);
                    if(id_.equals(m.getID()) && pw_.equals(m.getPW())){
                        Intent intent = new Intent(MainActivity.this , View_page.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , join_page.class);
                startActivity(intent);
            }
        });
    }

}
