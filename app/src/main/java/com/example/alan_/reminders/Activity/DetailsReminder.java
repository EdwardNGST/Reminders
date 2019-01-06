package com.example.alan_.reminders.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.alan_.reminders.Model.DatabaseHelper;
import com.example.alan_.reminders.Model.RemindersAdapter;
import com.example.alan_.reminders.R;
import java.util.Objects;

public class DetailsReminder extends AppCompatActivity {

    private Context context;
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;
    private AlertDialog dialog;

    private int id;
    private String title;
    private String text;
    private int priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_reminder);
        context=getApplicationContext();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        RelativeLayout layout_title = findViewById(R.id.layout_title);
        TextView lblTitle2 = findViewById(R.id.lblTitle2);
        TextView lblDescription2 = findViewById(R.id.lblDescription2);

        localDB = new DatabaseHelper(context);
        Intent intent = getIntent();
        String message = intent.getStringExtra(RemindersAdapter.EXTRA_MESSAGE);
        Cursor res = localDB.getReminders(Integer.parseInt(message));


        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            //Cada una de las posiciones del registro obtenido por el cursor se asignan a una variable
            id = res.getInt(0);
            title = res.getString(1);
            text = res.getString(2);
            priority = res.getInt(3);

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

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(context, com.example.alan_.reminders.MainActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int idSelected = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (idSelected == R.id.action_delete) {
            localDB.deleteReminder(id);
            Toast.makeText(context, "Recordatorio Eliminado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, com.example.alan_.reminders.MainActivity.class);
            startActivity(intent);
            return true;
        }else if (idSelected == R.id.action_edit){
            showDialogUpdate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialogUpdate() {
        AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View mView = getLayoutInflater().inflate(R.layout.dialog_update, null);
        //Se enlaza la vista con el Builder
        final EditText txtTitle = mView.findViewById(R.id.txtTitle);
        final EditText txtDesc = mView.findViewById(R.id.txtDesc);
        RadioGroup rg = mView.findViewById(R.id.rg);
        RadioButton rb1 = mView.findViewById(R.id.rb1);
        RadioButton rb2 = mView.findViewById(R.id.rb2);
        RadioButton rb3 = mView.findViewById(R.id.rb3);
        TextView lblOk = mView.findViewById(R.id.lblOk);
        TextView lblCancel = mView.findViewById(R.id.lblCancel);
        final int[] priorityChecked = new int[1];
        priorityChecked[0]=0;

        txtTitle.setText(title);
        txtDesc.setText(text);
        switch (priority){
            case 1:
                rb1.setChecked(true);
                rb2.setChecked(false);
                rb3.setChecked(false);
                priorityChecked[0]=1;
                break;
            case 2:
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                priorityChecked[0]=2;
                break;
            case 3:
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(true);
                priorityChecked[0]=3;
                break;
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        priorityChecked[0]=1;
                        break;
                    case R.id.rb2:
                        priorityChecked[0]=2;
                        break;
                    case R.id.rb3:
                        priorityChecked[0]=3;
                        break;
                    default:
                        priorityChecked[0]=0;
                }
            }
        });
        
        lblOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDB.updateReminder(id, txtTitle.getText().toString(), txtDesc.getText().toString(), priorityChecked[0]);
                dialog.hide();
                Toast.makeText(context, "Recordatorio Actualizado", Toast.LENGTH_SHORT).show();
                startActivity(getIntent());
            }
        });
        lblCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        //Muestra el dialogo
        dialog.show();
    }

}
