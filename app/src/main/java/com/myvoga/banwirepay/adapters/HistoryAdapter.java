package com.myvoga.banwirepay.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.viewHolders.HistoryViewHolder;
import com.myvoga.banwirepay.interfaces.IHistoryCallback;
import com.myvoga.banwirepay.models.HistoryModel;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private List<HistoryModel> items;
    private IHistoryCallback callback;

    public HistoryAdapter(List<HistoryModel> items, IHistoryCallback callback) {
        this.items = items;
        this.callback = callback;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_history, null, false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(v,callback);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int i) {
        holder.bind(items.get(i));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
