package com.example.projekat1.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projekat1.R;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

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
        initListeners();
    }

    private void init(View view){
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        prioritySpinner = (Spinner) view.findViewById(R.id.prioritySpinner);
        addButton = (Button) view.findViewById(R.id.addButtonNew);
        estimated = (EditText) view.findViewById(R.id.estimatedET);
        title = (EditText) view.findViewById(R.id.titleET);
        description = (EditText) view.findViewById(R.id.descriptionET);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        //stavljanje stvari u spinnere
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

    }

    private void initListeners(){
            addButton.setOnClickListener(v -> {
                System.out.println("KURACCC");
                if(checkIfAllSelected()){
//                    Ticket ticket = new Ticket(typeSpinner.getSelectedItem().toString(), prioritySpinner.getSelectedItem().toString(),
//                            Integer.parseInt(estimated.getText().toString()),title.getText().toString(),description.getText().toString());

                    Ticket ticket = new Ticket("asdf","asdf",12,"new","ccccc");
                    sharedViewModel.addTodoTicket(ticket);
                } else {
//                    Toast.makeText(this.getActivity(), "You have to fill in all the fields first.", Toast.LENGTH_LONG).show();
                    showErrorToast();
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

    private void showErrorToast(){
        Toast toast = Toast.makeText(this.getActivity(), "All fields must be filled in to create ticket", Toast.LENGTH_SHORT);
        View view = toast.getView();
        //Gets the actual oval background of the Toast then sets the colour filter
        view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        //Gets the TextView from the Toast so it can be editted
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
        toast.show();
    }
}
