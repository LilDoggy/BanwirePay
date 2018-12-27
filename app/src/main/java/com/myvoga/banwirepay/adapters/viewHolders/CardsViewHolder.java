package com.myvoga.banwirepay.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.interfaces.IItemSelected;

import io.card.payment.CreditCard;

public class CardsViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private TextView bankAndType;
    private TextView tvNumberCard;
    private TextView tvDate;
    private ImageView imgDelete;
    private IItemSelected callback;

    public CardsViewHolder(@NonNull View itemView, IItemSelected callback) {
        super(itemView);
        this.itemView = itemView;
        this.bankAndType = itemView.findViewById(R.id.tvBank);
        this.tvNumberCard = itemView.findViewById(R.id.tvCardNumber);
        this.tvDate = itemView.findViewById(R.id.tvDate);
        this.imgDelete = itemView.findViewById(R.id.imgDelete);
        this.callback = callback;
    }

    public void bind(final CreditCard card){

        bankAndType.setText(card.getCardType().name);
        tvDate.setText("Vencimiento: " + card.expiryMonth + "/" + card.expiryYear);
        String lastCardNumber = card.cardNumber.substring(card.cardNumber.length() - 4, card.cardNumber.length());
        tvNumberCard.setText("******** " + lastCardNumber);

        if (callback == null){
            imgDelete.setVisibility(View.GONE);
        }else{
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.callbackSelected(card,getAdapterPosition());
                }
            });
        }
    }
}
