package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.squareup.picasso.Picasso;

/**
 * Show the details of a particular deals including their sale vs regular price as well as description of the product.
 * Created by ramyav on 3/26/2018.
 */

public class DealDetailsActivity extends Activity implements DealsConstants {

    TextView mSalePrice, mRegularPrice, mTitle, mDescription;
    Button mAddToListButton, mAddToCartButton;
    ImageView mProductImage;
    DealsListModel mDealItem;
    int mPosition;
    long time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_details_layout);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mDealItem = bundle.getParcelable(DEAL_ITEM);
            mPosition = bundle.getInt(SIGNATURE_TIME);
        }

        mProductImage = (ImageView) findViewById(R.id.deal_list_item_image_view);
        mSalePrice = (TextView) findViewById(R.id.salePriceTextView);
        mRegularPrice = (TextView) findViewById(R.id.regularPriceTextView);
        mTitle = (TextView) findViewById(R.id.titleTextView);
        mDescription = (TextView) findViewById(R.id.descriptionTextView);
        mAddToListButton = (Button) findViewById(R.id.addToListButton);
        mAddToCartButton = (Button) findViewById(R.id.addToCartButton);

        setValuesToViews();
    }

    /**
     * Set values to the details activity views
     */
    private void setValuesToViews() {
        String url = mDealItem.getImage();
        Glide.with(this).load(url)
                .placeholder(R.drawable.spinner_animation)
                //TODO: Uncomment this to bring a different content for same URL. Hack was to change signature each time.
                //.signature(new StringSignature(String.valueOf(time)))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mProductImage);
        // Display Sale price in the details and strike through the regular price ONLY if on sale
        if(mDealItem.salePrice != null) {
            mSalePrice.setText(mDealItem.salePrice);
            mRegularPrice.setPaintFlags(mRegularPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        mRegularPrice.setText(mDealItem.price);
        mTitle.setText(mDealItem.title);
        mDescription.setText(mDealItem.description);
        mDescription.setMovementMethod(new ScrollingMovementMethod());
    }
}
