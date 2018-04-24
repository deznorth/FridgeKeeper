package com.deznorth.fridgekeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<FridgeItem> mValues;


    public RecyclerViewAdapter(List<FridgeItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fridge_item, parent, false);



        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);

        switch (mValues.get(position).ftype){
            case 1: //groceries
                holder.mImageView.setImageResource(R.drawable.ic_groceries);
                break;
            case 2: //leftovers
                holder.mImageView.setImageResource(R.drawable.ic_leftovers);
                break;
            case 3: //takeout
                holder.mImageView.setImageResource(R.drawable.ic_takeout);
                break;
                default:
                    holder.mImageView.setImageResource(R.drawable.ic_leftovers);
                    break;
        }

        holder.mNameView.setText(mValues.get(position).name);
        holder.mDateView.setText(mValues.get(position).date);
        holder.mAdderView.setText(mValues.get(position).adder); //mValues.get(position).adder

        holder.mThrashIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FridgeFragment ff = new FridgeFragment();
                ff.thrashItem(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mNameView;
        public final TextView mDateView;
        public final TextView mAdderView;
        public final ImageView mThrashIcon;
        public FridgeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.item_img);
            mNameView = view.findViewById(R.id.item_name);
            mDateView = view.findViewById(R.id.item_date);
            mAdderView = view.findViewById(R.id.Item_AddedBy);
            mThrashIcon = view.findViewById(R.id.item_thrash);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDateView.getText() + "'";
        }
    }


}
