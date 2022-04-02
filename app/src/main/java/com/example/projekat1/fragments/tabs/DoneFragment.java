package com.example.projekat1.fragments.tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekat1.R;
import com.example.projekat1.recycler.TicketAdapter;
import com.example.projekat1.recycler.TicketDiffItemCallback;
import com.example.projekat1.viewModels.SharedViewModel;

public class DoneFragment extends Fragment {

    private RecyclerView recyclerView;
    private SharedViewModel sharedViewModel;
    private TicketAdapter ticketAdapter;
    private EditText searchDoneTickets;

    public DoneFragment() {
        super(R.layout.fragment_done);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        initView(view);
        initObservers();
        initRecycler();
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.listRvDone);
        searchDoneTickets = view.findViewById(R.id.searchDoneTickets);
    }

    private void initObservers() {
        sharedViewModel.getTicketsDoneLiveData().observe(getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });

        searchDoneTickets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                sharedViewModel.filterDoneTickets(s.toString());
            }
        });
    }

    private void initRecycler() {
        ticketAdapter = new TicketAdapter( sharedViewModel,new TicketDiffItemCallback(), ticket -> {//todo ovde sam stavio SP, mozda je losa ideja
            Toast.makeText(getActivity(), ticket.getId() + "", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }
}
