package org.vitaltransformation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.vitaltransformation.R;
import org.vitaltransformation.adapters.EventsRecyclerViewAdapter;
import org.vitaltransformation.utils.BaseFragment;
import org.vitaltransformation.utils.DummyData;

import static org.vitaltransformation.adapters.EventsRecyclerViewAdapter.OnEventsAdapterInteractionListener;


public class EventsFragment extends BaseFragment implements OnEventsAdapterInteractionListener {
    public static final String TAG = "EventsFragment";

    private OnEventsInteractionListener mListener;

    private RecyclerView recyclerView;

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        EventsRecyclerViewAdapter adapter = new EventsRecyclerViewAdapter(DummyData.createEvents(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onViewDetailClicked() {
        EventDetailFragment detailFragment = EventDetailFragment.newInstance("");
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventsInteractionListener) {
            mListener = (OnEventsInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnEventsInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/


    public interface OnEventsInteractionListener {
        void onFragmentInteraction();
    }
}
