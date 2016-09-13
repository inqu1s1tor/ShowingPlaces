package com.aleksnik.showingplaces.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.ui.adapters.CategoryOfPlacesAdapter;
import com.aleksnik.showingplaces.util.ConnectivityChecker;


public class CategoryOfPlacesFragment extends GenericFragment {

    private GridView categoryOfPlacesGridView;
    private CategoryOfPlacesAdapter categoryOfPlacesAdapter;
    private boolean isConnected;

    private String[] categoryPlaces = new String[]{"ATM", "Bank", "Bar", "Restaurant", "Cafe", "Electronics", "Airport", "Doctor", "Cinema", "Coffee", "Hospital", "Taxi"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_of_places_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        categoryOfPlacesGridView = (GridView) view.findViewById(R.id.category_of_places_grid_view);
        categoryOfPlacesAdapter = new CategoryOfPlacesAdapter(getActivity(), categoryPlaces);
        categoryOfPlacesGridView.setAdapter(categoryOfPlacesAdapter);

        AdapterView.OnItemClickListener itemClicker = new ItemClicker();
        categoryOfPlacesGridView.setOnItemClickListener(itemClicker);

        isConnected = checkConnectivity();

    }

    @Override
    public void onStart() {
        super.onStart();
        categoryOfPlacesGridView.setAdapter(categoryOfPlacesAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_item_cover_area:
                mActivityBridge.getFragmentLauncher().launchSettingsFragment();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkConnectivity() {

        if (!ConnectivityChecker.isPlayServiceArePresents(getActivity())) {
            showLongToast("Play Services are missed");
            return false;
        }

        if (!ConnectivityChecker.isNetworkAvailable(getActivity())) {
            showLongToast("Wi-Fi/Internet is not active.");
            return false;
        }

        return true;
    }

    private final class ItemClicker implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String selectedCategory = (String) categoryOfPlacesAdapter.getItem(position);
            Log.d("TAG", "onItemClick: " + selectedCategory);
            Log.d("TAG", "isConnected: ");
            if (isConnected)
                mActivityBridge.getFragmentLauncher().launchNearestPlacesFragment(selectedCategory);
            else {
                showLongToast("Connect to the internet");
            }
        }
    }


}
