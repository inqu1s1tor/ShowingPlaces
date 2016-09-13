package com.aleksnik.showingplaces.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.model.NearestPlace;

import java.util.List;


public class NearestPlacesAdapter extends RecyclerView.Adapter<NearestPlacesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NearestPlace nearestPlace);
    }

    private List<NearestPlace> nearestPlaces;
    private final OnItemClickListener listener;


    public NearestPlacesAdapter(List<NearestPlace> nearestPlaces, OnItemClickListener listener) {
        this.nearestPlaces = nearestPlaces;
        this.listener = listener;
    }


    @Override
    public NearestPlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place_recycler_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(nearestPlaces.get(position), listener);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namePlace;
        public TextView addressPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            this.namePlace = (TextView) itemView.findViewById(R.id.name_place_text_view);
            this.addressPlace = (TextView) itemView.findViewById(R.id.address_place_text_view);
        }

        public void bind(final NearestPlace nearestPlace, final OnItemClickListener listener) {

            namePlace.setText(nearestPlace.getNamePlace());
            addressPlace.setText(nearestPlace.getAddressPlace());


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(nearestPlace);

                }

            });

        }
    }

    @Override
    public int getItemCount() {
        return nearestPlaces.size();
    }
}

