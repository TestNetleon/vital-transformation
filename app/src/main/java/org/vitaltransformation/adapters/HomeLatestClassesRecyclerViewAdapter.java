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
import org.vitaltransformation.model.Classes;

import java.util.ArrayList;

public class HomeLatestClassesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROW = 0;
    private final int BOTTOM = 1;
    private ArrayList<Classes> events;
    private Context context;
    private OnHomeClassesAdapterInteractionListener listener;

    public HomeLatestClassesRecyclerViewAdapter(ArrayList<Classes> classes,
                                                OnHomeClassesAdapterInteractionListener listener) {
        this.events = classes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();

        if (viewType == ROW)
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_home_latest_classes, viewGroup, false));
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
            holder.duration.setText(events.get(position).getDuration());

        } else if (viewHolder instanceof BottomViewHolder) {

            BottomViewHolder holder = (BottomViewHolder) viewHolder;

            holder.viewMore.setText(R.string.view_all_classes);
            holder.viewMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onViewAllClassesClicked();
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

        private TextView title, duration;
        private ImageView imageView;

        MyViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            duration = view.findViewById(R.id.duration);
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

    public interface OnHomeClassesAdapterInteractionListener {
        void onViewAllClassesClicked();
    }
}

