package com.example.kimminseong.babyhelper;

import android.app.Activity;
import android.content.Intent;
import android.net.LinkAddress;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.List;

/**
 * Created by kimminseong on 2017-10-31.
 */

public class list extends Activity implements ListViewAdapter.ListControl{

    String tbName;
    CreateDB db;
    ListView listView;
    ListViewAdapter adapter;
    int selectNumber;

    TextView user_name;
    ImageView user_img;
    TextView join_day;

    TextView user_name2;
    ImageView user_img2;
    TextView join_day2;

    TextView user_name3;
    ImageView user_img3;
    TextView join_day3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tbName = "MEMBER";

        db = new CreateDB(list.this,
                tbName,
                null,1);

        db.makeDB();

        final List list = db.getUSER();

        adapter = new ListViewAdapter();

        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        Button btn_plus = (Button)findViewById(R.id.plus);
        Button btn_write = (Button)findViewById(R.id.write);
        Listview_item member;

        user_img = (ImageView)findViewById(R.id.user_img);
        user_name = (TextView)findViewById(R.id.user_name);
        join_day = (TextView)findViewById(R.id.join_day);

        user_img2 = (ImageView)findViewById(R.id.user_img2);
        user_name2 = (TextView)findViewById(R.id.user_name2);
        join_day2 = (TextView)findViewById(R.id.join_day2);

        user_img3 = (ImageView)findViewById(R.id.user_img3);
        user_name3 = (TextView)findViewById(R.id.user_name3);
        join_day3 = (TextView)findViewById(R.id.join_day3);

        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearItem();
                String name = user_name.getText().toString();
                List list_view = db.getList(name);
                Listview_item member;
                for(int i = 0 ; i < list_view.size();i++){
                    member = (Listview_item) list_view.get(i);
                    Log.d("tagX",member.getPath()+" "+member.getDay()+" "+member.getComment());
                    adapter.addItem(member.getKey(),member.getPath() , member.getDay() , member.getComment());
                }
                adapter.notifyDataSetChanged();
            }
        });

        user_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearItem();
                String name = user_name2.getText().toString();
                List list_view = db.getList(name);
                Listview_item member;
                for(int i = 0 ; i < list_view.size();i++){
                    member = (Listview_item) list_view.get(i);
                    Log.d("tagX",member.getPath()+" "+member.getDay()+" "+member.getComment());
                    adapter.addItem(member.getKey(),member.getPath() , member.getDay() , member.getComment());
                }
                adapter.notifyDataSetChanged();
            }
        });


        user_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearItem();
                String name = user_name3.getText().toString();
                List list_view = db.getList(name);
                Listview_item member;
                for(int i = 0 ; i < list_view.size();i++){
                    member = (Listview_item) list_view.get(i);
                    Log.d("tagX",member.getPath()+" "+member.getDay()+" "+member.getComment());
                    adapter.addItem(member.getKey(),member.getPath() , member.getDay() , member.getComment());
                }
                adapter.notifyDataSetChanged();
            }
        });


        if(list.size()!= 0) {

            member p = (member)list.get(0);
            Uri path = Uri.parse(p.getID());
            user_img.setImageURI(path);
            join_day.setText(p.getNAME());
            user_name.setText(p.getPW());

            if(2<=list.size()) {
                p = (member) list.get(1);
                path = Uri.parse(p.getID());
                user_img2.setImageURI(path);
                join_day2.setText(p.getNAME());
                user_name2.setText(p.getPW());
            }


            if(3==list.size()) {
                p = (member) list.get(2);
                path = Uri.parse(p.getID());
                user_img3.setImageURI(path);
                join_day3.setText(p.getNAME());
                user_name3.setText(p.getPW());
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                int count , checked;
                count = adapter.getCount();
                //adapter.notifyDataSetChanged();
                if(count > 0){}
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(list.this , push.class);
                startActivityForResult(intent,1);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(list.this , story.class);
                startActivityForResult(intent,0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(this, "add complete : " +resultCode , Toast.LENGTH_SHORT).show();
        //adapter.notifyDataSetChanged();
        // Check which request we're responding to
           if (resultCode == RESULT_OK) {
               //Toast.makeText(this, "add complete" , Toast.LENGTH_SHORT).show();
               Listview_item member;
               final List list_view = db.getList(user_name.getText().toString());
               adapter.clearItem();
               for(int i = 0 ; i < list_view.size();i++){
                   member = (Listview_item) list_view.get(i);
                   Log.d("tagX",member.getPath()+" "+member.getDay()+" "+member.getComment());
                   adapter.addItem(member.getKey(),member.getPath() , member.getDay() , member.getComment());
               }
               adapter.notifyDataSetChanged();
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)

            }

            if(resultCode == 2){
                final List list = db.getUSER();

                member p = (member)list.get(0);
                Uri path = Uri.parse(p.getID());
                user_img.setImageURI(path);
                join_day.setText(p.getNAME());
                user_name.setText(p.getPW());

                if(2<=list.size()) {
                    p = (member) list.get(1);
                    path = Uri.parse(p.getID());
                    user_img2.setImageURI(path);
                    join_day2.setText(p.getNAME());
                    user_name2.setText(p.getPW());
                }


                if(3<=list.size()) {
                    p = (member) list.get(2);
                    path = Uri.parse(p.getID());
                    user_img3.setImageURI(path);
                    join_day3.setText(p.getNAME());
                    user_name3.setText(p.getPW());
                }
            }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    public void selectItem(int pos){
        db.delList(pos);
        adapter.clearItem();
        Listview_item member;
        final List list_view = db.getList(user_name.getText().toString());
        for(int i = 0 ; i < list_view.size();i++){
            member = (Listview_item) list_view.get(i);
            Log.d("tagX",member.getPath()+" "+member.getDay()+" "+member.getComment());
            adapter.addItem(member.getKey(),member.getPath() , member.getDay() , member.getComment());
        }
        adapter.notifyDataSetChanged();
    }
}
