package com.myvoga.banwirepay.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.viewHolders.CardsViewHolder;
import com.myvoga.banwirepay.interfaces.IItemSelected;

import java.util.List;

import io.card.payment.CreditCard;

public class CardsAdapter extends RecyclerView.Adapter<CardsViewHolder> {

    private List<CreditCard> items;
    private Context context;
    private IItemSelected callback;

    public CardsAdapter(List<CreditCard> items, Context context, IItemSelected callback) {
        this.items = items;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, null, false);
        CardsViewHolder viewHolder = new CardsViewHolder(v,callback);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder cardsViewHolder, int i) {
        cardsViewHolder.bind(items.get(i));
        cardsViewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
