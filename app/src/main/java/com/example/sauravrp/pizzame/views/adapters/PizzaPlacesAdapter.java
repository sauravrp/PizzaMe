package com.example.sauravrp.pizzame.views.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sauravrp.pizzame.R;
import com.example.sauravrp.pizzame.databinding.ResultDetailsListItemBinding;
import com.example.sauravrp.pizzame.models.ui.ResultUiModel;

import java.util.List;

public class PizzaPlacesAdapter extends RecyclerView.Adapter<PizzaPlacesAdapter.ViewHolder> {

    private List<ResultUiModel> data;

    public PizzaPlacesAdapter(List<ResultUiModel> aData) {
        data = aData;
    }

    public void onResultClicked(ResultUiModel resultUiModel) {
        Log.d("temp", resultUiModel.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ResultDetailsListItemBinding itemBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()),
                       R.layout.result_details_list_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResultUiModel result = data.get(position);
        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

       private final ResultDetailsListItemBinding binding;

        public ViewHolder(ResultDetailsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ResultUiModel item) {
            binding.setResult(item);
            binding.executePendingBindings();
        }
    }
}
