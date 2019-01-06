package com.example.alan_.reminders.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alan_.reminders.Model.DatabaseHelper;
import com.example.alan_.reminders.R;

public class RegisterFragment extends Fragment {

    private EditText txtTitle, txtDesc;
    private RadioGroup rg;
    private Button btnRegister;

    private int priority;

    private Context context;
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        context=getContext();

        localDB = new DatabaseHelper(context);
        declareElements(view);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newReminder();
            }
        });

        //Listener del radiogroup que nos permite al dar en registrar saber que radiobutton esta seleccionado
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        priority=1;
                        break;
                    case R.id.rb2:
                        priority=2;
                        break;
                    case R.id.rb3:
                        priority=3;
                        break;
                    default:
                        priority=0;
                }
            }
        });

        return view;
    }

    private void declareElements(View view){
        txtTitle=view.findViewById(R.id.txtTitle);
        txtDesc=view.findViewById(R.id.txtDesc);
        rg=view.findViewById(R.id.rg);
        btnRegister=view.findViewById(R.id.btnRegister);
    }

    private void newReminder(){
        final String title=txtTitle.getText().toString();
        final String text=txtDesc.getText().toString();
        localDB.insertNewReminder(title, text, priority);
        Toast.makeText(context, "Recordatorio agregado correctamente", Toast.LENGTH_SHORT).show();
        cleanForm();
    }

    private void cleanForm(){
        txtTitle.setText("");
        txtDesc.setText("");
        rg.check(0);
    }
}
