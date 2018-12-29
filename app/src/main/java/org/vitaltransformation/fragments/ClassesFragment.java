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
import org.vitaltransformation.adapters.ClassesRecyclerViewAdapter;
import org.vitaltransformation.utils.BaseFragment;
import org.vitaltransformation.utils.DummyData;

public class ClassesFragment extends BaseFragment {

    public static final String TAG = "ClassesFragment";

    private OnClassesInteractionListener mListener;

    private RecyclerView recyclerView;


    public static ClassesFragment newInstance() {
        return new ClassesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ClassesRecyclerViewAdapter adapter = new ClassesRecyclerViewAdapter(DummyData.createClasses());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClassesInteractionListener) {
            mListener = (OnClassesInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClassesInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/


    public interface OnClassesInteractionListener {
        void onFragmentInteraction();
    }
}
