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

        addTestTickets();//napunjeno sa filler ticketima

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


    public void addTodoTicket(Ticket ticket) {//good
        ticket.setId(counter++);
        ticketsTodoTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
        ticketsTodoLiveData.setValue(listToSubmit);
    }

    public void removeTodoTicket(Ticket ticket) {//good
        if (ticketsTodoTempList.contains(ticket)){
            ticketsTodoTempList.remove(ticket);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
            ticketsTodoLiveData.setValue(listToSubmit);
        }
    }

    public void moveTicketToProgress(Ticket ticket) {//good
        ticket.setProgress(MainActivity.IN_PROGRESS);
        ticketsInProgressTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsInProgressTempList);
        ticketsInProgressLiveData.setValue(listToSubmit);
        removeTodoTicket(ticket);
    }

    public void removeProgressTicket(Ticket ticket){
        if (ticketsInProgressTempList.contains(ticket)){
            ticketsInProgressTempList.remove(ticket);
            ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsInProgressTempList);
            ticketsInProgressLiveData.setValue(listToSubmit);
        }
    }

    public void moveTicketToTodo(Ticket ticket){
        ticket.setProgress(MainActivity.TODO);
        ticketsTodoTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsTodoTempList);
        ticketsTodoLiveData.setValue(listToSubmit);
        removeProgressTicket(ticket);
    }

    public void moveTicketToDone(Ticket ticket) {
        ticket.setProgress(MainActivity.DONE);
        ticketsDoneTempList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(ticketsDoneTempList);
        ticketsDoneLiveData.setValue(listToSubmit);
        removeProgressTicket(ticket);
    }

    //samo za testiranje dodati ticketi
    private void addTestTickets(){
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket("Bug","Low",5,"Test ticket bug" + counter, "Ovo je test ticket za bug");
            Ticket ticket2 = new Ticket("Enhancement","Medium",5,"Test ticket " + counter, "Ovo je test ticket za enhancement");
            counter++;
            if(counter %2 == 0) ticketsTodoTempList.add(ticket);
            else ticketsTodoTempList.add(ticket2);
        }

        Ticket ticket3 = new Ticket("Bug","Low",5,"Test ticket PROGRESS" + counter++, "Ovo je test ticket za bug");
        Ticket ticket4 = new Ticket("Enhancement","Low",5,"Test ticket PROGRESS" + counter++, "Ovo je test ticket za Enhancement");
        Ticket ticket5 = new Ticket("Enhancement","Low",5,"Test ticket PROGRESS" + counter++, "Ovo je test ticket za Enhancement");
        ticket3.setProgress(MainActivity.IN_PROGRESS);
        ticket5.setProgress(MainActivity.IN_PROGRESS);
        ticket4.setProgress(MainActivity.IN_PROGRESS);
        ticketsInProgressTempList.add(ticket3);
        ticketsInProgressTempList.add(ticket5);
        ticketsInProgressTempList.add(ticket4);

        Ticket ticket6 = new Ticket("Bug","Low",5,"Test ticket DONE" + counter++, "Ovo je test ticket za bug");
        Ticket ticket7 = new Ticket("Enhancement","Medium",5,"Test ticket DONE" + counter++, "Ovo je test ticket za Enhancement");
        ticket7.setProgress(MainActivity.DONE);
        ticket7.setProgress(MainActivity.DONE);
        ticketsDoneTempList.add(ticket7);
        ticketsDoneTempList.add(ticket7);
    }

}

