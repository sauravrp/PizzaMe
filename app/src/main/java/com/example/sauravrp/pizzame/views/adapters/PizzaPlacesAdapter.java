package com.example.sauravrp.pizzame.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.models.Result;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PizzaPlacesAdapter extends RecyclerView.Adapter<PizzaPlacesAdapter.ViewHolder> {

    private ArrayList<Result> data;

    private Context context;

    public PizzaPlacesAdapter(Context ctx, ArrayList<Result> aData) {
        context = ctx;
        data = aData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_details_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Result result = data.get(position);

        holder.name.setText(result.getTitle());
        holder.address.setText( String.format(context.getString(R.string.address_spec), result.getAddress(), result.getCity(), result.getState()));
        holder.distance.setText(String.format(context.getString(R.string.distance), result.getDistance()));
        holder.phoneNumber.setText(result.getPhone());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.address)
        TextView address;

        @BindView(R.id.phone_number)
        TextView phoneNumber;

        @BindView(R.id.distance)
        TextView distance;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
