package com.example.projekat1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projekat1.R;
import com.example.projekat1.activities.MainActivity;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewTicketFragment extends Fragment {

    private Spinner typeSpinner;
    private Spinner prioritySpinner;
    private Button addButton;
    private EditText estimated;
    private EditText title;
    private EditText description;
    private SharedViewModel sharedViewModel;

    public NewTicketFragment() {
        super(R.layout.fragment_new_ticket);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view){
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        prioritySpinner = (Spinner) view.findViewById(R.id.prioritySpinner);
        addButton = (Button) view.findViewById(R.id.addButton);
        estimated = (EditText) view.findViewById(R.id.estimatedET);
        title = (EditText) view.findViewById(R.id.titleET);
        description = (EditText) view.findViewById(R.id.descriptionET);
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

        //https://stackoverflow.com/questions/47791790/disabling-the-first-item-in-a-spinner
        //todo namesti hint od spinnera



        addButton.setOnClickListener(v ->{

            if(checkIfAllSelected()){
            Ticket ticket = new Ticket(
                    typeSpinner.getSelectedItem().toString(),
                    prioritySpinner.getSelectedItem().toString(),
                    Integer.parseInt(estimated.getText().toString()),
                    title.getText().toString(),
                    description.getText().toString());

                if(checkIfAllSelected()){
                    sharedViewModel.addTicket(ticket);//todo proveri da li radi!!!!!!!!!!!!!
                }

                //recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
            }
            else{
                Toast.makeText(this.getActivity(), "All fields must be filled",Toast.LENGTH_LONG).show();
            }


        });

    }

    private boolean checkIfAllSelected(){
        return !title.getText().toString().equals("")
                && !description.getText().toString().equals("")
                && !estimated.getText().toString().equals("")
                && !typeSpinner.getSelectedItem().toString().equals("") //todo promeni equals da bude pravilno zbog hinta
                && !prioritySpinner.getSelectedItem().toString().equals("");//todo promeni equals da bude pravilno zbog hinta
    }

}
