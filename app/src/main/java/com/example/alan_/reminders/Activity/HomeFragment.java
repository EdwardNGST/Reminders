package com.example.alan_.reminders.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alan_.reminders.Model.DatabaseHelper;
import com.example.alan_.reminders.Model.Reminders;
import com.example.alan_.reminders.Model.RemindersAdapter;
import com.example.alan_.reminders.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static View.OnClickListener clickListener;
    private OnFragmentInteractionListener mListener;
    private Context context;
    private RecyclerView rvReminders;
    private ArrayList<Reminders> remindersList;
    private RemindersAdapter remindersAdapter;
    //El DatabaseHelper es la base de nuestra base de datos local
    private DatabaseHelper localDB;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        clickListener = new ClickListener(context);
        rvReminders = view.findViewById(R.id.rvListReminders);
        remindersList=new ArrayList<>();
        remindersAdapter=new RemindersAdapter(context, remindersList);
        rvReminders.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
        rvReminders.setLayoutManager(manager);
        rvReminders.setAdapter(remindersAdapter);
        rvReminders.setHasFixedSize(true);

        localDB = new DatabaseHelper(context);

        Cursor res=localDB.getReminders();

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

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else { }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class ClickListener implements View.OnClickListener {
        private final Context context;
        public ClickListener(Context context) {
            this.context=context;
        }

        @Override
        public void onClick(View view) {
            //goToDetails(view);
            Intent intent = new Intent(context, com.example.alan_.reminders.Activity.DetailsReminder.class);
            startActivityForResult(intent, 0);
        }

        private void goToDetails(View v){
            int seleccionado;
            RecyclerView.ViewHolder contenedor;
            // Obtener la posicion del elemento
            seleccionado = rvReminders.getChildAdapterPosition(v);

            // Obtener la vista empaquetada
            contenedor = rvReminders.findViewHolderForAdapterPosition(seleccionado);
        }
    }
}
