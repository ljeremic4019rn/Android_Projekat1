package com.example.projekat1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.projekat1.R;
import com.example.projekat1.activities.MainActivity;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

public class EditTicketFragment extends Fragment {
    private Spinner typeSpinner;
    private Spinner prioritySpinner;
    private Button editButton;
    private EditText estimated;
    private EditText title;
    private EditText description;
    private SharedViewModel sharedViewModel;
    private Ticket ticket;

    public EditTicketFragment() {
        super(R.layout.fragment_edit_ticket);
//        this.ticket = ticket;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initOvservers();

//        System.out.println("U TICKETU " + ticket.getTitle());
    }

    private void initView(View view){
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinnerEdit);
        prioritySpinner = (Spinner) view.findViewById(R.id.prioritySpinnerEdit);
        editButton = (Button) view.findViewById(R.id.editButton);
        estimated = (EditText) view.findViewById(R.id.estimatedETEdit);
        title = (EditText) view.findViewById(R.id.titleETEdit);
        description = (EditText) view.findViewById(R.id.descriptionETEdit);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.priority, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.ticket_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        prioritySpinner.setAdapter(priorityAdapter);
        typeSpinner.setAdapter(typeAdapter);
    }


    private void initOvservers(){
        editButton.setOnClickListener(v -> {

        });
    }
}
