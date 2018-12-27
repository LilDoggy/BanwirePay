package com.myvoga.banwirepay.adapters.viewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout rootCell;

    public PayViewHolder(@NonNull View itemView, IItemSelected callback, Context context) {
        super(itemView);
        this.itemView = itemView;
        this.tvNum = itemView.findViewById(R.id.tvNum);
        this.tvDesc = itemView.findViewById(R.id.tvDescription);
        this.tvAmount = itemView.findViewById(R.id.tvAmount);
        this.tvRef = itemView.findViewById(R.id.tvRef);
        //this.imgPay = itemView.findViewById(R.id.imgPay);
        this.tvDate = itemView.findViewById(R.id.tvDate);
        this.callback = callback;
        this.context = context;
        this.rootCell = itemView.findViewById(R.id.rootCell);
    }

    public void bind(final PayModel model){

        tvNum.setText("#" + model.getId());
        tvNum.setLines(1);
        tvDesc.setText(model.getDescription());
        tvDesc.setLines(1);
        tvRef.setText(model.getRef());
        tvRef.setLines(1);
        tvAmount.setText("$" + model.getAmount() + " MXN");
        tvAmount.setLines(1);
        tvDate.setText("Fecha: " + model.getDate());
        tvDate.setLines(1);

        if (!model.isPaid()){
            tvAmount.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            //imgPay.setVisibility(View.VISIBLE);

           /* imgPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callback != null)
                        callback.callbackSelected(model,getAdapterPosition());
                }
            });*/
        }
    }

}
