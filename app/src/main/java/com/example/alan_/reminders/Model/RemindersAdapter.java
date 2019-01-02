package com.example.alan_.reminders.Model;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan_.reminders.Activity.HomeFragment;
import com.example.alan_.reminders.R;

import java.util.ArrayList;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ContainerView> {
    private ArrayList<Reminders> remindersList;
    public RemindersAdapter(ArrayList<Reminders> reminders){ this.remindersList=reminders; }
    @NonNull
    @Override
    public RemindersAdapter.ContainerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_reminders,
                viewGroup, false);
        view.setOnClickListener(HomeFragment.clickListener);
        return new ContainerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindersAdapter.ContainerView containerView, int i) {
        Reminders reminders = remindersList.get(i);
        containerView.lblTitleTask.setText(reminders.getTitle());
        containerView.lblTextTask.setText(reminders.getText());

        switch (reminders.getPriority()){
            case 1:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
            case 2:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FFFFEA00"));
                break;
            case 3:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FF64DD17"));
                break;
            default:
                containerView.priorityColor.setBackgroundColor(Color.parseColor("#FFF"));
        }
    }

    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public class ContainerView extends RecyclerView.ViewHolder {
        ImageView priorityColor;
        TextView lblTitleTask;
        TextView lblTextTask;
        public ContainerView(@NonNull View itemView) {
            super(itemView);
            this.priorityColor=itemView.findViewById(R.id.priorityColor);
            this.lblTitleTask=itemView.findViewById(R.id.lblTitleTask);
            this.lblTextTask=itemView.findViewById(R.id.lblTextTask);
        }
    }
}
