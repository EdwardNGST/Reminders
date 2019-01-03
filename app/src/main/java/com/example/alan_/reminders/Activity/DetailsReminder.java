package com.example.alan_.reminders.Activity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alan_.reminders.Model.DatabaseHelper;
import com.example.alan_.reminders.Model.Reminders;
import com.example.alan_.reminders.R;

public class DetailsReminder extends AppCompatActivity {

    private RelativeLayout layout_title;
    private TextView lblTitle2, lblDescription2;
    private Context context;
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_reminder);
        context=getApplicationContext();
        layout_title=findViewById(R.id.layout_title);
        lblTitle2=findViewById(R.id.lblTitle2);
        lblDescription2=findViewById(R.id.lblDescription2);

        localDB = new DatabaseHelper(context);
        Cursor res = localDB.getReminders(2);

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            //Cada una de las posiciones del registro obtenido por el cursor se asignan a una variable
            int id = res.getInt(0);
            String title = res.getString(1);
            String text = res.getString(2);
            int priority = res.getInt(3);

            switch (priority){
                case 1:
                    layout_title.setBackgroundColor(Color.parseColor("#FF0000"));
                    break;
                case 2:
                    layout_title.setBackgroundColor(Color.parseColor("#FFFFEA00"));
                    break;
                case 3:
                    layout_title.setBackgroundColor(Color.parseColor("#FF64DD17"));
                    break;
                default:
                    layout_title.setBackgroundColor(Color.parseColor("#FFF"));
                    break;
            }

            lblTitle2.setText(title);
            lblDescription2.setText(text);
        }
    }

}
