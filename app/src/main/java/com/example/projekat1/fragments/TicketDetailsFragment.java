package com.example.projekat1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projekat1.R;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

public class TicketDetailsFragment extends Fragment {
    private TextView type;
    private TextView title;
    private TextView priority;
    private TextView estimation;
    private TextView description;
    private Button loggedTimeButton;
    private ImageView image;
    private int buttonNum;
    private Ticket ticket;

    public TicketDetailsFragment(Ticket ticket) {
        super(R.layout.fragment_ticket_details);
        this.ticket = ticket;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        buttonNum = ticket.getLoggedTime();
        initText();
        initObservers();
    }

    private void initViews(View view){
        type = view.findViewById(R.id.tdType);
        title = view.findViewById(R.id.tdTitle);
        priority = view.findViewById(R.id.tdPriority);
        image = view.findViewById(R.id.ticketDetailsImg);
        estimation = view.findViewById(R.id.tdEstimation);
        description = view.findViewById(R.id.tdDescription);
        loggedTimeButton = view.findViewById(R.id.tdLoggedButton);

    }

    private void initText(){
        type.setText(ticket.getType());
        title.setText(ticket.getTitle());
        priority.setText(ticket.getPriority());
        description.setText(ticket.getDescription());
        estimation.setText(String.valueOf(ticket.getEstimated()));
        loggedTimeButton.setText(String.valueOf(buttonNum));

        if (ticket.getType().equals("Bug")){
            image.setImageResource(R.drawable.ic_baseline_bug_report_24);
        }
        else image.setImageResource(R.drawable.ic_engance);
    }

    private void initObservers(){//todo ako je u done da se ne prikazuje dugme
        loggedTimeButton.setOnClickListener(v ->{
            loggedTimeButton.setText(String.valueOf(++buttonNum));
            ticket.setLoggedTime(buttonNum);
        });

    }
}
