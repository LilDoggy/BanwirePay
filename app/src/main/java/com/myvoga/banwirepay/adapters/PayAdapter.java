package com.myvoga.banwirepay.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.viewHolders.PayViewHolder;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.models.PayModel;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayViewHolder> {

    private List<PayModel> items;
    private Context context;
    private IItemSelected callback;

    public PayAdapter(List<PayModel> items, Context context, IItemSelected callback) {
        this.items = items;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public PayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pays, null, false);
        PayViewHolder holder = new PayViewHolder(v,callback,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PayViewHolder holder, int i) {
        holder.bind(items.get(i));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
