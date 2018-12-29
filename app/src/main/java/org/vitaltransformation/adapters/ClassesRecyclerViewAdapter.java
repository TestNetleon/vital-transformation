package org.vitaltransformation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.model.Classes;

import java.util.ArrayList;

public class ClassesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROW = 0;
    private final int BOTTOM = 1;

    private ArrayList<Classes> classes;
    private Context context;

    public ClassesRecyclerViewAdapter(ArrayList<Classes> classes) {
        this.classes = classes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewType == ROW)
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_classes, viewGroup, false));
        else
            return new BottomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_view_all, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;

            holder.imageView.setBackgroundResource(R.drawable.dummy_image);
            holder.title.setText(classes.get(position).getTitle());
            holder.classes.setText(String.format(context.getResources().getString(R.string.classes_1),
                    classes.get(position).getClasses()));

            if (position % 2 == 0) {
                ConstraintLayout.LayoutParams paramsTitle = (ConstraintLayout.LayoutParams) holder.title.getLayoutParams();
                paramsTitle.horizontalBias = 1;
                holder.title.setLayoutParams(paramsTitle);
                holder.title.setGravity(Gravity.END);

                ConstraintLayout.LayoutParams paramsClasses = (ConstraintLayout.LayoutParams) holder.classes.getLayoutParams();
                paramsClasses.horizontalBias = 1;
                holder.classes.setLayoutParams(paramsClasses);

            } else {
                ConstraintLayout.LayoutParams paramsTitle = (ConstraintLayout.LayoutParams) holder.title.getLayoutParams();
                paramsTitle.horizontalBias = 0;
                holder.title.setLayoutParams(paramsTitle);
                holder.title.setGravity(Gravity.START);

                ConstraintLayout.LayoutParams paramsClasses = (ConstraintLayout.LayoutParams) holder.classes.getLayoutParams();
                paramsClasses.horizontalBias = 0;
                holder.classes.setLayoutParams(paramsClasses);
            }
        } else{
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
        if (position == classes.size())
            return BOTTOM;
        else
            return ROW;
    }

    @Override
    public int getItemCount() {
        return classes.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, classes;
        private ImageView imageView;

        MyViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            classes = view.findViewById(R.id.classes);
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
}
