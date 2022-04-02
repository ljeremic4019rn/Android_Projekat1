package com.example.projekat1.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projekat1.activities.MainActivity;
import com.example.projekat1.models.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SharedViewModel extends ViewModel {

    public static int counter = 1;

    private final MutableLiveData<List<Ticket>> ticketsTodoLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> ticketsInProgressLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> ticketsDoneLiveData = new MutableLiveData<>();


    private ArrayList<Ticket> ticketsTodoTempList = new ArrayList<>();
    private ArrayList<Ticket> ticketsInProgressTempList = new ArrayList<>();
    private ArrayList<Ticket> ticketsDoneTempList = new ArrayList<>();


    public SharedViewModel() {
        for (int i = 0; i < 10; i++) {
           Ticket ticket = new Ticket("Bug","Low",5,"Test ticket bug" + counter, "Ovo je test ticket za bug");
            Ticket ticket2 = new Ticket("Enhancement","Medium",5,"Test ticket " + counter, "Ovo je test ticket");

            Ticket ticket3 = new Ticket("Bug","Low",5,"Test ticket PROGRESS" + counter, "Ovo je test ticket za bug");
            ticket3.setProgress(MainActivity.IN_PROGRESS);
            Ticket ticket4 = new Ticket("Bug","Low",5,"Test ticket DONE" + counter, "Ovo je test ticket za bug");
            ticket4.setProgress(MainActivity.DONE);

            ticket.setId(counter++);

            ticketsInProgressTempList.add(ticket3);
            ticketsDoneTempList.add(ticket4);

            if(counter %2 == 0)
            ticketsTodoTempList.add(ticket);
            else ticketsTodoTempList.add(ticket2);

        }

        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
        ticketsTodoLiveData.setValue(listToSubmit);
        ArrayList<Ticket> listToSubmit2 = new ArrayList<>(ticketsInProgressTempList);
        ticketsInProgressLiveData.setValue(listToSubmit2);
        ArrayList<Ticket> listToSubmit3 = new ArrayList<>(ticketsDoneTempList);
        ticketsDoneLiveData.setValue(listToSubmit3);
    }

    public MutableLiveData<List<Ticket>> getTicketsTodoLiveData() {
        return ticketsTodoLiveData;
    }

    public MutableLiveData<List<Ticket>> getTicketsInProgressLiveData() {
        return ticketsInProgressLiveData;
    }

    public MutableLiveData<List<Ticket>> getTicketsDoneLiveData() {
        return ticketsDoneLiveData;
    }

    public void filterTickets(String filter) {//todo popravi
        List<Ticket> filteredList = ticketsTodoTempList.stream().filter(ticket -> ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        ticketsTodoLiveData.setValue(filteredList);
    }

//    public LiveData<List<Ticket>> getTickets() {
//        return ticketsTodoLiveData;
//    }


    public void addTicket(Ticket ticket) {
        ticket.setId(counter++);
        ticketsTodoTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
        ticketsTodoLiveData.setValue(listToSubmit);
    }

    public void removeTicket(int id) {
        Optional<Ticket> ticketObject = ticketsTodoTempList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if (ticketObject.isPresent()) {
            ticketsTodoTempList.remove(ticketObject.get());
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
            ticketsTodoLiveData.setValue(listToSubmit);
        }
    }

}

