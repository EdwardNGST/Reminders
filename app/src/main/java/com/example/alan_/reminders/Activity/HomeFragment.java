package com.example.alan_.reminders.Activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.alan_.reminders.Model.DatabaseHelper;
import com.example.alan_.reminders.Model.Reminders;
import com.example.alan_.reminders.Model.RemindersAdapter;
import com.example.alan_.reminders.R;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Context context = getContext();
        RecyclerView rvReminders = view.findViewById(R.id.rvListReminders);
        ArrayList<Reminders> remindersList = new ArrayList<>();
        RemindersAdapter remindersAdapter = new RemindersAdapter(context, remindersList);
        rvReminders.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
        rvReminders.setLayoutManager(manager);
        rvReminders.setAdapter(remindersAdapter);
        rvReminders.setHasFixedSize(true);

        DatabaseHelper localDB = new DatabaseHelper(context);

        Cursor res= localDB.getReminders();

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            //Cada una de las posiciones del registro obtenido por el cursor se asignan a una variable
            int id = res.getInt(0);
            String title = res.getString(1);
            String text = res.getString(2);
            int priority = res.getInt(3);

            //Se agrega la tarea a la lista que esta enlazada con el recyclerview, por lo tanto se actualiza el recyclerview
            remindersList.add(new Reminders(id, title, text, priority));
        }

        return view;
    }
}
