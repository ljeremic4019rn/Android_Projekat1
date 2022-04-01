package com.example.projekat1.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.projekat1.models.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SharedViewModel extends ViewModel {

    public static int counter = 1;

    private final MutableLiveData<List<Ticket>> ticketsLiveData = new MutableLiveData<>();
    private ArrayList<Ticket> ticketsTempList = new ArrayList<>();

    public SharedViewModel() {
        for (int i = 0; i < 10; i++) {
           Ticket ticket = new Ticket("Bug","Low",5,"Test ticket bug" + counter, "Ovo je test ticket za bug");
            Ticket ticket2 = new Ticket("Enhancement","Medium",5,"Test ticket " + counter, "Ovo je test ticket");
            ticket.setId(counter++);

            if(counter %2 == 0)
            ticketsTempList.add(ticket);
            else ticketsTempList.add(ticket2);

        }

        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
        ticketsLiveData.setValue(listToSubmit);
    }

    public MutableLiveData<List<Ticket>> getTicketsLiveData() {
        return ticketsLiveData;
    }

    public void filterTickets(String filter) {//todo proveri
        List<Ticket> filteredList = ticketsTempList.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        ticketsLiveData.setValue(filteredList);
    }

    public LiveData<List<Ticket>> getTickets() {
        return ticketsLiveData;
    }


    public void addTicket(Ticket ticket) {
        ticket.setId(counter++);
        ticketsTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
        ticketsLiveData.setValue(listToSubmit);
    }

    public void removeTicket(int id) {
        Optional<Ticket> ticketObject = ticketsTempList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            ticketsTempList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTempList);
            ticketsLiveData.setValue(listToSubmit);
        }
    }

}

