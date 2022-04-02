package com.example.projekat1.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projekat1.R;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

import java.util.List;

public class StatisticsFragment extends Fragment {

    private TextView todoNum1;
    private TextView todoNum2;
    private TextView todoNum3;
    private TextView progressNum1;
    private TextView progressNum2;
    private TextView progressNum3;
    private TextView doneNum1;
    private TextView doneNum2;
    private TextView doneNum3;
    private SharedViewModel sharedViewModel;

    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setStatistics();
    }

    private void initViews(View view){
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        todoNum1 = view.findViewById(R.id.numTodo1);
        todoNum2 = view.findViewById(R.id.numToDo2);
        todoNum3 = view.findViewById(R.id.numToDo3);
        progressNum1 = view.findViewById(R.id.numProgress1);
        progressNum2 = view.findViewById(R.id.numProgress2);
        progressNum3 = view.findViewById(R.id.numProgress3);
        doneNum1 = view.findViewById(R.id.numDone1);
        doneNum2 = view.findViewById(R.id.numDone2);
        doneNum3 = view.findViewById(R.id.numDone3);


    }


    private void setStatistics(){
        List<Ticket> todoList = sharedViewModel.getTicketsTodoLiveData().getValue();
        List<Ticket> progressList = sharedViewModel.getTicketsInProgressLiveData().getValue();
        List<Ticket> doneList = sharedViewModel.getTicketsDoneLiveData().getValue();
//todo ovo ima problema

//        sharedViewModel.getTicketsTodoLiveData().observe(getViewLifecycleOwner(), tickets -> {
//            System.out.println("kurac " + tickets.size());
//        });


//        int test = todoList.size();

//        progressNum1.setText(progressList.size());
//        doneNum1.setText(doneList.size());



    }

}
