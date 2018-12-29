package org.vitaltransformation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.vitaltransformation.R;

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ROW = 2;
    private final int TOP = 1;

    private final String[] titles = {"Home", "My Cart", "My Orders", "Events", "Classes", "Contact Us", "About Us", "Logout"};
    private final int[] icons = {R.drawable.ic_add, R.drawable.ic_add, R.drawable.ic_add, R.drawable.ic_add,
            R.drawable.ic_add, R.drawable.ic_add, R.drawable.ic_add, R.drawable.ic_add};

    private Context context;
    private OnDrawerAdapterInteractionListener mListener;

    public DrawerAdapter(OnDrawerAdapterInteractionListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewType == ROW)
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_drawer_item, viewGroup, false));
        else
            return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.header_drawer, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof MyViewHolder) {
            MyViewHolder holder = (MyViewHolder) viewHolder;
            holder.title.setText(titles[position - 1]);
            holder.title.setCompoundDrawables(ContextCompat.getDrawable(context, icons[position - 1]),
                    null, null, null);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClicked(viewHolder.getAdapterPosition() - 1);
                }
            });

        } else if (viewHolder instanceof HeaderViewHolder) {

            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;

            /*holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TOP;
        else
            return ROW;
    }

    @Override
    public int getItemCount() {
        return titles.length + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private View view;

        MyViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            title = view.findViewById(R.id.title);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        HeaderViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.name);
        }
    }

    public interface OnDrawerAdapterInteractionListener {
        void onItemClicked(int position);
    }
}
