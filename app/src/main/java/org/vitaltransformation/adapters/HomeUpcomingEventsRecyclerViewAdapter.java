package org.vitaltransformation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.model.UpcomingEvent;

import java.util.ArrayList;

public class HomeUpcomingEventsRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<UpcomingEvent> events;
    private Context context;

    public HomeUpcomingEventsRecyclerViewAdapter(ArrayList<UpcomingEvent> classes) {
        this.events = classes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_upcoming_event, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.date.setText(events.get(position).getDate());
            holder.month.setText(events.get(position).getMonth());
            holder.time.setText(events.get(position).getTime());
            holder.event.setText(String.format("%s at %s", events.get(position).getEvent(), events.get(position).getLocation()));
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView date, month, time, event;
        private final View view;

        MyViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            date = view.findViewById(R.id.date);
            month = view.findViewById(R.id.month);
            time = view.findViewById(R.id.time);
            event = view.findViewById(R.id.event);
        }
    }
}
