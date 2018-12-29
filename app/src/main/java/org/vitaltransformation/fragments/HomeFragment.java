package org.vitaltransformation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.vitaltransformation.R;
import org.vitaltransformation.adapters.HomeEventsRecyclerViewAdapter;
import org.vitaltransformation.adapters.HomeLatestClassesRecyclerViewAdapter;
import org.vitaltransformation.adapters.HomeUpcomingEventsRecyclerViewAdapter;
import org.vitaltransformation.utils.BaseFragment;
import org.vitaltransformation.utils.DummyData;

import static org.vitaltransformation.adapters.HomeEventsRecyclerViewAdapter.OnHomeEventsAdapterInteractionListener;
import static org.vitaltransformation.adapters.HomeLatestClassesRecyclerViewAdapter.OnHomeClassesAdapterInteractionListener;


public class HomeFragment extends BaseFragment implements
        OnHomeClassesAdapterInteractionListener,
        OnHomeEventsAdapterInteractionListener {

    public static final String TAG = "HomeFragment";

    private LinearLayout linDetails;
//    private ImageView imgProfile;
    private TextView name, notificationCount, btnDonate, subscription, btnUpgrade, className,
            time, btnRegister, textEvents;
    private RecyclerView recyclerUpcomingEvent, recyclerClasses, recyclerEvents;

    private OnHomeFragmentInteractionListener mListener;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linDetails = view.findViewById(R.id.linDetails);
//        imgProfile = view.findViewById(R.id.imgProfile);
        name = view.findViewById(R.id.name);
        notificationCount = view.findViewById(R.id.notificationCount);
        btnDonate = view.findViewById(R.id.btnDonate);
        subscription = view.findViewById(R.id.subscription);
        btnUpgrade = view.findViewById(R.id.btnUpgrade);
        recyclerUpcomingEvent = view.findViewById(R.id.recyclerUpcomingEvent);
        className = view.findViewById(R.id.className);
        time = view.findViewById(R.id.time);
        btnRegister = view.findViewById(R.id.btnRegister);
        recyclerClasses = view.findViewById(R.id.recyclerClasses);
        textEvents = view.findViewById(R.id.textEvents);
        recyclerEvents = view.findViewById(R.id.recyclerEvents);

        recyclerUpcomingEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerClasses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerUpcomingEvent.setNestedScrollingEnabled(false);
        recyclerEvents.setNestedScrollingEnabled(false);
        recyclerClasses.setNestedScrollingEnabled(false);

        HomeUpcomingEventsRecyclerViewAdapter adapterUpcomingEvents
                = new HomeUpcomingEventsRecyclerViewAdapter(DummyData.createLatestEvents());
        recyclerUpcomingEvent.setAdapter(adapterUpcomingEvents);

        HomeLatestClassesRecyclerViewAdapter adapterLatestClasses
                = new HomeLatestClassesRecyclerViewAdapter(DummyData.createLatestClasses(), this);
        recyclerClasses.setAdapter(adapterLatestClasses);

        HomeEventsRecyclerViewAdapter adapterEvents
                = new HomeEventsRecyclerViewAdapter(DummyData.createEvents(), this);
        recyclerEvents.setAdapter(adapterEvents);

//        Picasso.with(getContext()).load(R.drawable.dummy_image).fit().into(imgProfile);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentInteractionListener) {
            mListener = (OnHomeFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewAllClassesClicked() {
        if (mListener != null)
            mListener.viewAllClasses();
    }

    @Override
    public void onViewAllEventsClicked() {
        if (mListener != null)
            mListener.viewAllEvents();
    }

    public interface OnHomeFragmentInteractionListener {

        void viewAllClasses();

        void viewAllEvents();

    }
}
