package com.example.kimminseong.babyhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kimminseong on 2017-10-31.
 */

public class join_page extends Activity{

    private CreateDB db;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        String tbName = "MEMBER";

        db = new CreateDB(join_page.this,
                tbName,
                null,1);

        db.makeDB();

        final EditText id = (EditText)findViewById(R.id.enter_id);
        final EditText pw = (EditText)findViewById(R.id.enter_pw);
        final EditText name = (EditText)findViewById(R.id.enter_name);
        final EditText phone = (EditText)findViewById(R.id.enter_phone);



        Button join_btn = (Button)findViewById(R.id.join);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_ = id.getText().toString();
                String pw_ = pw.getText().toString();
                String name_ = name.getText().toString();
                String phone_ = phone.getText().toString();
                db.add(id_ , pw_ , name_ , phone_);
                Intent intent = new Intent(join_page.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
