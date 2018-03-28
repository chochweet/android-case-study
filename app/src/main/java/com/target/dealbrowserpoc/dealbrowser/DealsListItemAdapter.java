package com.target.dealbrowserpoc.dealbrowser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;

import java.util.ArrayList;

/**
 * Adapter class to get the data and attach them to respective views through ViewHolder
 * Created by ramyav on 3/24/2018.
 */

public class DealsListItemAdapter extends RecyclerView.Adapter<DealsListItemAdapter.DealItemViewHolder> implements DealsConstants {
    private ArrayList<DealsListModel> mDealsList;
    private Context mContext;
    int mLayoutType;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public class DealItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView productImage;
        public TextView title, price;
        public Button aisleBtn;
        private ItemClickListener clickListener;

        public DealItemViewHolder(View view) {
            super(view);
            productImage = (ImageView) view.findViewById(R.id.deal_list_item_image_view);
            title = (TextView) view.findViewById(R.id.deal_list_item_title);
            price = (TextView) view.findViewById(R.id.deal_list_item_price);
            aisleBtn = (Button) view.findViewById(R.id.aisleLabel);
            view.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    /**
     * Constructor.
     *
     * @param context    Context
     * @param dealsList  ArrayList<DealsListModel> List of DealsListModel objects
     * @param layoutType Linear or Grid Layout
     */
    public DealsListItemAdapter(Context context, ArrayList<DealsListModel> dealsList, int layoutType) {
        this.mContext = context;
        this.mDealsList = dealsList;
        this.mLayoutType = layoutType;
    }

    @Override
    public DealItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutType == 0 ? R.layout.deal_list_item : R.layout.deal_list_item_grid, null);
        DealItemViewHolder mv = new DealItemViewHolder(itemView);
        return mv;
    }

    @Override
    public void onBindViewHolder(DealItemViewHolder holder, int position) {
        if (mDealsList != null) {
            final DealsListModel dealItem = mDealsList.get(position);
            final long time = System.currentTimeMillis();
            String url = dealItem.getImage();
            ImageView imageView = holder.productImage;
            // Was added to test Picasso and its speed in loading
//            Picasso.with(mContext).load(url)
//                    .placeholder(R.drawable.ic_launcher)
//                    .into(imageView);
            // Glide is faster to bring the bitmaps back by few seconds
            Glide.with(mContext).load(url)
                    .placeholder(R.drawable.spinner_animation)
                    //TODO: Uncomment this to bring a different content for same URL. Hack was to change signature each time. Save the time for the details page
                    //.signature(new StringSignature(String.valueOf(time)))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
            holder.title.setText(dealItem.title);
            // Display Sale price in the list and the product is not on sale show the regular price
            if (dealItem.salePrice != null) {
                holder.price.setText(dealItem.salePrice);
            } else {
                holder.price.setText(dealItem.price);
            }
            holder.aisleBtn.setText(dealItem.aisle);

            holder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(mContext, DealDetailsActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putParcelable(DEAL_ITEM, dealItem);
                    mBundle.putInt(DEAL_ITEM_POSITION, position);
                    mBundle.putLong(SIGNATURE_TIME, time);
                    intent.putExtras(mBundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDealsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
