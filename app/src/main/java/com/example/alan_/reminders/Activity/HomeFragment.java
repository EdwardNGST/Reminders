package com.example.alan_.reminders.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        remindersAdapter=new RemindersAdapter(remindersList);
        rvReminders.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager manager=new LinearLayoutManager(context);
        rvReminders.setLayoutManager(manager);
        rvReminders.setAdapter(remindersAdapter);
        rvReminders.setHasFixedSize(true);

        remindersList.add(new Reminders(1, "Recordatorio 1", "Recordatorio texto1", 1));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
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
        public ClickListener(Context context) {
        }

        @Override
        public void onClick(View view) {

        }
    }
}
