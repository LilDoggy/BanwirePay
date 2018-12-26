package com.myvoga.banwirepay.adapters.viewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.models.PayModel;

public class PayViewHolder extends RecyclerView.ViewHolder {


    private View itemView;
    private TextView tvNum;
    private TextView tvDesc;
    private TextView tvAmount;
    private TextView tvRef;
    private TextView tvDate;
    private ImageView imgPay;
    private IItemSelected callback;
    private Context context;

    public PayViewHolder(@NonNull View itemView, IItemSelected callback, Context context) {
        super(itemView);
        this.itemView = itemView;
        this.tvNum = itemView.findViewById(R.id.tvNum);
        this.tvDesc = itemView.findViewById(R.id.tvDescription);
        this.tvAmount = itemView.findViewById(R.id.tvAmount);
        this.tvRef = itemView.findViewById(R.id.tvRef);
        this.imgPay = itemView.findViewById(R.id.imgPay);
        this.tvDate = itemView.findViewById(R.id.tvDate);
        this.callback = callback;
        this.context = context;
    }

    public void bind(final PayModel model){
        tvNum.setText("#" + model.getId());
        tvDesc.setText(model.getDescription());
        tvRef.setText(model.getRef());
        tvAmount.setText("$ " + model.getAmount() + " MXN");
        tvDate.setText("Fecha: " + model.getDate());

        if (!model.isPaid()){
            tvAmount.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            imgPay.setVisibility(View.VISIBLE);

            imgPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null)
                        callback.callbackSelected(model,getAdapterPosition());
                }
            });
        }
    }

}
