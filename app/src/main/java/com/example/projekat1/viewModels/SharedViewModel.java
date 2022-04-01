package com.example.projekat1.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projekat1.models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class SharedViewModel extends ViewModel {

    public static int counter = 1;

    private ArrayList<Ticket> ticketsTmp = new ArrayList<>();
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();

    public SharedViewModel() {
        for (int i = 0; i < 10; i++) {
           Ticket ticket = new Ticket("bug","Low",5,"Test ticket", "Ovo je test ticket");
           ticket.setId(counter++);
            ticketsTmp.add(ticket);
        }
        tickets.setValue(ticketsTmp);
    }

    public MutableLiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public void filterTickets(String filter) {//todo proveri
        List<Ticket> filteredList = ticketsTmp.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        tickets.setValue(filteredList);
    }

    public void addTicket(Ticket ticket){
        ticketsTmp.add(ticket);
        ArrayList<Ticket> newTickets = new ArrayList<>(ticketsTmp);
        tickets.setValue(newTickets);
    }

    public void removeTicket(int id){//todo ovo ce mozda morati da se menja kasnije
        Optional<Ticket> carObject = ticketsTmp.stream().filter(car -> car.getId() == id).findFirst();
        if (carObject.isPresent()) {
            ticketsTmp.remove(carObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTmp);
            tickets.setValue(listToSubmit);
        }
    }

}

