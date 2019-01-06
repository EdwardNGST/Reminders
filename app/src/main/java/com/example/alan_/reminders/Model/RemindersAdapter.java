package com.example.alan_.reminders.Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alan_.reminders.R;

import java.util.ArrayList;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ContainerView> {
    public static final String EXTRA_MESSAGE = "com.example.alan_.reminders.MESSAGE";
    private ArrayList<Reminders> remindersList;
    private Context context;

    public RemindersAdapter(Context context, ArrayList<Reminders> reminders){
        this.remindersList=reminders;
        this.context=context;
    }
    @NonNull
    @Override
    public RemindersAdapter.ContainerView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_reminders,
                viewGroup, false);
        //view.setOnClickListener(HomeFragment.clickListener);

        return new ContainerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RemindersAdapter.ContainerView containerView, int i) {
        final Reminders reminders = remindersList.get(i);
        containerView.lblTitleTask.setText(reminders.getTitle());
        containerView.lblTextTask.setText(reminders.getText());
        containerView.id=reminders.getId();

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

        containerView.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.alan_.reminders.Activity.DetailsReminder.class);
                intent.putExtra(EXTRA_MESSAGE, reminders.getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    class ContainerView extends RecyclerView.ViewHolder {
        int id;
        ImageView priorityColor;
        TextView lblTitleTask;
        TextView lblTextTask;
        CardView cardView;
        ContainerView(@NonNull View itemView) {
            super(itemView);
            this.priorityColor=itemView.findViewById(R.id.priorityColor);
            this.lblTitleTask=itemView.findViewById(R.id.lblTitleTask);
            this.lblTextTask=itemView.findViewById(R.id.lblTextTask);
            this.cardView=itemView.findViewById(R.id.cv);
        }
    }
}
