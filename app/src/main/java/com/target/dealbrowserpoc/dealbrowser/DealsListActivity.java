package com.target.dealbrowserpoc.dealbrowser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.target.dealbrowserpoc.dealbrowser.deals.DealsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Main Activity that fetches the data (deals) from the server and displays as a list in its fragment
 */

public class DealsListActivity extends Activity {

    private List<DealsList.DealDetail> detailsList = new ArrayList<>();
    private ArrayList<DealsListModel> detailsListModel = new ArrayList<>();
    Switch switchAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDealsList();
    }

    /**
     * GET Deals List from server
     **/
    private void getDealsList() {
        DealsAPI apiInterface = GetDealsController.getClient().create(DealsAPI.class);
        Call<DealsList> call = apiInterface.getDeals();
        call.enqueue(new Callback<DealsList>() {
            @Override
            public void onResponse(Call<DealsList> call, Response<DealsList> response) {

                Log.d("TAG", response.code() + "");
                DealsList resource = response.body();
                Integer id = resource.id;
                setDetailsList(resource.data);
                callView();
            }

            @Override
            public void onFailure(Call<DealsList> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void callView() {
        for (DealsList.DealDetail detail : getDetailsList()) {
            DealsListModel model = new DealsListModel();
            model.setIndex(detail.index);
            model.set_id(detail._id);
            model.setAisle(detail.aisle);
            model.setDescription(detail.description);
            model.setTitle(detail.title);
            model.setImage(detail.image);
            model.setPrice(detail.price);
            model.setSalePrice(detail.salePrice);
            detailsListModel.add(model);
        }
        Fragment frag = DealsListFragment.newInstance(detailsListModel, switchAB.isChecked() ? 1 : 0);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, frag, "Test Fragment");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        switchAB = (Switch) menu.findItem(R.id.switchId)
                .getActionView().findViewById(R.id.switchLayout);

        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Fragment frag;
                if (isChecked) {
                    Toast.makeText(getApplication(), "Grid Layout", Toast.LENGTH_SHORT)
                            .show();
                    frag = DealsListFragment.newInstance(detailsListModel, 1);
                } else {
                    Toast.makeText(getApplication(), "Linear Layout", Toast.LENGTH_SHORT)
                            .show();
                    frag = DealsListFragment.newInstance(detailsListModel, 0);
                }
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container, frag, "Test Fragment");
                transaction.commit();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public List<DealsList.DealDetail> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DealsList.DealDetail> detailsList) {
        this.detailsList = detailsList;
    }
}
