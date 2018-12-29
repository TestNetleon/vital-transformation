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
import org.vitaltransformation.model.Events;

import java.util.ArrayList;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROW = 0;
    private final int BOTTOM = 1;

    private ArrayList<Events> events;
    private Context context;
    private OnEventsAdapterInteractionListener mListener;

    public EventsRecyclerViewAdapter(ArrayList<Events> classes,
                                     OnEventsAdapterInteractionListener mListener) {
        this.events = classes;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewType == ROW)
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_events, viewGroup, false));
        else
            return new BottomViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_view_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.imageView.setBackgroundResource(R.drawable.dummy_image);
            holder.title.setText(events.get(position).getTitle());
            holder.time.setText(events.get(position).getTime());
            holder.location.setText(events.get(position).getLocation());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onViewDetailClicked();
                }
            });

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
        if (position == events.size())
            return BOTTOM;
        else
            return ROW;
    }

    @Override
    public int getItemCount() {
        return events.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, time, location;
        private ImageView imageView;
        private View view;

        MyViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            title = view.findViewById(R.id.title);
            time = view.findViewById(R.id.time);
            location = view.findViewById(R.id.location);
            imageView = view.findViewById(R.id.imgBackground);
        }
    }

    class BottomViewHolder extends RecyclerView.ViewHolder {

        private TextView viewMore;

        BottomViewHolder(@NonNull View view) {
            super(view);
            viewMore = view.findViewById(R.id.btnViewAll);
        }
    }

    public interface OnEventsAdapterInteractionListener {
        void onViewDetailClicked();
    }
}
