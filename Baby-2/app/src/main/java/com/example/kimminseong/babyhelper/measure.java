package com.example.kimminseong.babyhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kimminseong on 2017-10-31.
 */

public class measure extends Activity {

    private Button btn_dis_on;
    private Button btn_dis_off;
    private Button btn_wtmp_on;
    private Button btn_wtmp_off;

    private TextView tv_dis;
    private TextView tv_wtmp;

    int dis_flag=0; // 0 이 중지;
    int wtmp_flag=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

        btn_dis_on = (Button)findViewById(R.id.distance_on);
        btn_dis_off= (Button)findViewById(R.id.distance_off);
        btn_wtmp_on = (Button)findViewById(R.id.wtemp_on);
        btn_wtmp_off = (Button)findViewById(R.id.wtemp_off);

        tv_dis =  (TextView) findViewById(R.id.dis);
        tv_wtmp =  (TextView) findViewById(R.id.wtmp);

        btn_dis_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_dis.setText(View_page.distance);
            }
        });

        btn_dis_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_dis.setText("NO VALUE");
                dis_flag = 0;
            }
        });

        btn_wtmp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_wtmp.setText(View_page.wtemp);
            }
        });

        btn_wtmp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_wtmp.setText("NO VALUE");
            }
        });

    }


}
