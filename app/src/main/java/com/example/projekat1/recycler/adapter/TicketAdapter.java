package com.example.projekat1.recycler.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekat1.R;
import com.example.projekat1.activities.MainActivity;
import com.example.projekat1.models.Ticket;
import com.example.projekat1.viewModels.SharedViewModel;

import java.util.function.Consumer;


public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.ViewHolder> {

    private final Consumer<Ticket> onTicketClicked;
    public static SharedPreferences sharedPreferences;

    public TicketAdapter(SharedPreferences sharedPreferences, @NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onTicketClicked) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        TicketAdapter.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.accept(ticket);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    // unutrasnja klasa
    public static class ViewHolder extends RecyclerView.ViewHolder {

//        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
//            this.context = context;
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket) {
            ImageView imageView = itemView.findViewById(R.id.ticketIcon);
            ImageButton iTopButton = itemView.findViewById(R.id.ticketTopButton);
            ImageButton iButtonBottom = itemView.findViewById(R.id.ticketBottomButton);
            boolean isAdmin = false;

            ((TextView) itemView.findViewById(R.id.ticketTitleListItem)).setText(ticket.getTitle());
            ((TextView) itemView.findViewById(R.id.ticketDescriptionListItem)).setText(ticket.getDescription());

            if((sharedPreferences.getString(MainActivity.LOGGED_USER, "").contains("admin")))
                isAdmin = true;

            if (ticket.getType().equals("Bug")){
                imageView.setImageResource(R.drawable.ic_baseline_bug_report_24);
            }
            else imageView.setImageResource(R.drawable.ic_engance);



            if (ticket.getProgress().equals(MainActivity.TODO)){
                iTopButton.setImageResource(R.drawable.ic_arrow_right_24);
                if(isAdmin){
                    iButtonBottom.setImageResource(R.drawable.ic_remove);//ako je admin ima remove
                    iTopButton.setOnClickListener(t ->{
                        //todo set remove item from list
                    });
                }
                else iButtonBottom.setVisibility(View.INVISIBLE);//ako nije admin sakri dugme
            }
            else if (ticket.getProgress().equals(MainActivity.IN_PROGRESS)){
                //todo make after in progress recyclable
            }
            else{
                //todo make after done recyclable
            }

        }

    }
}
