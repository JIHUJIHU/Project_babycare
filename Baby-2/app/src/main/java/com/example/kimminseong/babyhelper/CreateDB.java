package com.example.kimminseong.babyhelper;

/**
 * Created by kimminseong on 2017-10-31.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimminseong on 2017-09-11.
 */

public class CreateDB extends SQLiteOpenHelper {

    private Context context;

    public CreateDB(Context context , String name, SQLiteDatabase.CursorFactory factory , int version){
        super(context , name, factory , version);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();

        sb.append("CREATE TABLE MEMBER (");
        sb.append("ID TEXT,");
        sb.append("PW TEXT,");
        sb.append("NAME TEXT,");
        sb.append("PHONE TEXT)");

        db.execSQL(sb.toString());

        Log.d("DB","create tb success");

        //Toast.makeText(context , "Table 생성 완료", Toast.LENGTH_SHORT).show();

        sb.setLength(0);
        sb.append("CREATE TABLE LIST (");
        sb.append("KEY_Value INTEGER PRIMARY KEY,");
        sb.append("NAME TEXT,");
        sb.append("PATH TEXT,");
        sb.append("DAY TEXT,");
        sb.append("COMMENT TEXT)");

        db.execSQL(sb.toString());

        //Toast.makeText(context , "Table2 생성 완료", Toast.LENGTH_SHORT).show();

        sb.setLength(0);
        sb.append("CREATE TABLE USER (");
        sb.append("PATH TEXT,");
        sb.append("NAME TEXT,");
        sb.append("DAY TEXT)");

        db.execSQL(sb.toString());

        //Toast.makeText(context , "Table3 생성 완료", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void makeDB(){
        SQLiteDatabase db = getReadableDatabase();
    }

    public void add(String id , String pw, String name, String phone){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO MEMBER ( ");
        sb.append(" ID , PW , NAME , PHONE) ");
        sb.append(" VALUES(?,?,?,?)");

        db.execSQL(sb.toString() , new Object[]{id,pw,name,phone});

        Log.d("DB","insert success");
    }



    public List get(){
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM MEMBER");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString() , null);

        List people = new ArrayList();
        member member = null;

        while(cursor.moveToNext()){
            member = new member();

            member.setID(cursor.getString(0));
            member.setPW(cursor.getString(1));
            member.setNAME(cursor.getString(2));
            member.setPHONE(cursor.getString(3));

            people.add(member);
        }
        return people;
    }

    public List getUSER(){
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM USER");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString() , null);

        List people = new ArrayList();
        member member = null;

        while(cursor.moveToNext()){
            member = new member();

            member.setID(cursor.getString(0)); // key
            member.setPW(cursor.getString(1)); // path
            member.setNAME(cursor.getString(2)); // day

            people.add(member);
        }
        return people;
    }

    public List getList(String name){
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT * FROM LIST WHERE NAME = '"+name+"'");

        Log.d("debugf_error","SELECT NAME FROM LIST WHERE NAME = '"+name+"'");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString() , null);

        List people = new ArrayList();
        Listview_item member = null;

        while(cursor.moveToNext()){
            member = new Listview_item();

            member.setKey(cursor.getInt(0));
            member.setName(cursor.getString(1));
            member.setPath(cursor.getString(2));
            member.setDay(cursor.getString(3));
            member.setComment(cursor.getString(4));

            people.add(member);
        }
        return people;
    }

    public void addUSER(String path , String name, String day){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO USER ( ");
        sb.append(" PATH , NAME , DAY) ");
        sb.append(" VALUES(?,?,?)");

        db.execSQL(sb.toString() , new Object[]{path,name,day});

        Log.d("DB","insert USER success");
    }

    public void addLIST(String name ,String path , String day,String comment){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();

        sb.append(" INSERT INTO LIST ( ");
        sb.append(" NAME, PATH , DAY , COMMENT) ");
        sb.append(" VALUES(?,?,?,?)");

        db.execSQL(sb.toString() , new Object[]{name,path,day,comment});

        Log.d("DB","insert path success");
    }

    public void delList(int key){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();

        sb.append(" DELETE FROM LIST ");
        sb.append(" WHERE KEY_value = (?)");

        db.execSQL(sb.toString() , new Object[]{key});

        Log.d("DB","insert path success");
    }
}