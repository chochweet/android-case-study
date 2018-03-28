package com.target.dealbrowserpoc.dealbrowser;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment class to show deals list as a Linear Layout or Grid Layout
 * Created by ramyav on 3/25/2018.
 */

public class DealsListFragment extends Fragment implements DealsConstants {
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    ArrayList<DealsListModel> dealsList;
    View rootView;
    DealsListItemAdapter dealsAdapter;
    RecyclerView dealsRecycler;
    int layoutType;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected DealsListFragment.LayoutManagerType mCurrentLayoutManagerType;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if (args != null) {
            dealsList = args.getParcelableArrayList(DEAL_LIST);
            layoutType = args.getInt(DEALS_LAYOUT_TYPE);
        }
    }

    /**
     *
     * Create a new instance of DealsListFragment, initialized to
     * show the deals in a chosen layout
     *
     * @param detailsArray ArrayList<DealsListModel> List of DealsListModel objects
     * @param layoutType Linear or Grid Layout
     */
    public static DealsListFragment newInstance(ArrayList<DealsListModel> detailsArray, int layoutType) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(DEAL_LIST, detailsArray);
        bundle.putInt(DEALS_LAYOUT_TYPE, layoutType);

        DealsListFragment fragment = new DealsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        rootView = inflater.inflate(R.layout.fragment_deal_list, container, false);
        dealsRecycler = (RecyclerView) rootView.findViewById(R.id.dealsRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        dealsRecycler.setLayoutManager(layoutManager);
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
            layoutType = (int) savedInstanceState.getInt(DEALS_LAYOUT_TYPE);
        }
        setRecyclerViewLayoutManager(layoutType);
        dealsAdapter = new DealsListItemAdapter(getActivity(), dealsList, layoutType);
        dealsRecycler.setAdapter(dealsAdapter);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        savedInstanceState.putInt(DEALS_LAYOUT_TYPE, layoutType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(int layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll mPosition.
        if (dealsRecycler.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) dealsRecycler.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case 1:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case 0:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        dealsRecycler.setLayoutManager(layoutManager);
        dealsRecycler.scrollToPosition(scrollPosition);
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll mPosition.
        if (dealsRecycler.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) dealsRecycler.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                layoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        dealsRecycler.setLayoutManager(layoutManager);
        dealsRecycler.scrollToPosition(scrollPosition);
    }
}
