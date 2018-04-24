package com.deznorth.fridgekeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.deznorth.fridgekeeper.MainActivity;

/**
 * A fragment representing a list of Items.
 */
public class FridgeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

//    public String mAdder;


    RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MainActivity.items);


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FridgeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FridgeFragment newInstance(int columnCount) {
        FridgeFragment fragment = new FridgeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fridge_item_list, container, false);

        // Set the adapter
            Context context = view.getContext();
            //mContext = context;
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mAdapter);

        return view;
    }

    public void addItem(int type, String name, String date, String adder, int dateType){
        //I might need to change this later on
        int size = MainActivity.items.size();

        MainActivity.items.add(size,new FridgeItem(size,name,date, adder,type,dateType));
        mAdapter.notifyItemRangeChanged(size,size);
        mAdapter.notifyItemInserted(size);
    }

    /** I LEFT HERE!!!!
     * HUGE PROBLEM. Removing wont work with my current ID system. I need to get an ID
     * from the actual item handler because my ID won't change after the others are
     * removed, meaning that I can't delete more than one or it will crash because
     * the ID of the other one is out of range. find a way to identify items in the list
     * that automatically refreshes when the range changes.
     * **/
    public void thrashItem(int index){
        if(MainActivity.items.size()>0) {
            MainActivity.items.remove(index);
            mAdapter.notifyItemRemoved(index);
            mAdapter.notifyItemRangeChanged(index, MainActivity.items.size());
            //mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

//    public String getAdder(){
//
//        Context c = getActivity();
//        String Adder = "";
//        if(c!=null){
//            SharedPreferences sharedPrefs = c.getSharedPreferences(
//                    getString(R.string.Shared_Prefs_Key), Context.MODE_PRIVATE);
//
//            Adder = sharedPrefs.getString(getString(R.string.profile_name_Key)
//                    ,getString(R.string.profile_default_name));
//        }
//
//        return Adder;
//    }

//    public void thrashItem(int[] indexes){
//        for(int i = 0; i<indexes.length; i++){
//            MainActivity.items.remove(indexes[i]);
//            mAdapter.notifyItemRemoved(indexes[i]);
//            mAdapter.notifyItemRangeChanged(indexes[i],MainActivity.items.size());
//        }
//
//    }

}
