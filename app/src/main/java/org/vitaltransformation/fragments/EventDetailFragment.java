package org.vitaltransformation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.vitaltransformation.R;
import org.vitaltransformation.adapters.EventTicketRecyclerViewAdapter;
import org.vitaltransformation.utils.BaseFragment;
import org.vitaltransformation.utils.DummyData;

public class EventDetailFragment extends BaseFragment {

    public static final String TAG = "EventDetailFragment";
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    private ImageView imgBackground;
    private TextView title, time, location;
    private RecyclerView recycler;

    private OnEventDetailInteractionListener mListener;

    public static EventDetailFragment newInstance(String param1) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgBackground = view.findViewById(R.id.imgBackground);
        title = view.findViewById(R.id.title);
        time = view.findViewById(R.id.time);
        location = view.findViewById(R.id.location);
        recycler = view.findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        EventTicketRecyclerViewAdapter adapter = new EventTicketRecyclerViewAdapter(DummyData.createTickets());
        recycler.setAdapter(adapter);


    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventDetailInteractionListener) {
            mListener = (OnEventDetailInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEventDetailInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    public interface OnEventDetailInteractionListener {
        void onFragmentInteraction();
    }
}
