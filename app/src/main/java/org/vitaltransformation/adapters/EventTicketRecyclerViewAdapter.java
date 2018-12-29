package org.vitaltransformation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.model.Ticket;

import java.util.ArrayList;

public class EventTicketRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROW = 0;
    private final int BOTTOM = 1;

    ArrayList<Ticket> tickets;
    private Context context;

    public EventTicketRecyclerViewAdapter(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == ROW)
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_event_ticket, viewGroup, false));
        else
            return new BottomViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_view_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.title.setText(tickets.get(position).getTitle());
            holder.price.setText("$" + tickets.get(position).getPrice());
            holder.count.setText("2");

        } else if (viewHolder instanceof BottomViewHolder) {

            BottomViewHolder holder = (BottomViewHolder) viewHolder;
            holder.viewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == tickets.size())
            return BOTTOM;
        else
            return ROW;
    }

    @Override
    public int getItemCount() {
        return tickets.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView count, price, title;
        private ImageView btnAdd, btnRemove;

        MyViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            count = view.findViewById(R.id.count);
            btnAdd = view.findViewById(R.id.btnAdd);
            btnRemove = view.findViewById(R.id.btnRemove);
        }
    }

    class BottomViewHolder extends RecyclerView.ViewHolder {

        private TextView viewMore;

        BottomViewHolder(@NonNull View view) {
            super(view);
            viewMore = view.findViewById(R.id.btnViewAll);
        }
    }
}
