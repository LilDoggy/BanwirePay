package com.myvoga.banwirepay.adapters.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.interfaces.IHistoryCallback;
import com.myvoga.banwirepay.models.HistoryModel;

public class HistoryViewHolder extends RecyclerView.ViewHolder{

    private View itemView;
    private TextView tvPosition;
    private TextView tvStatus;
    private TextView tvRef;
    private TextView tvAmount;
    private ImageView imEnter;
    private IHistoryCallback callback;

    public HistoryViewHolder(@NonNull View itemView, IHistoryCallback callback) {
        super(itemView);
        this.itemView = itemView;
        this.tvPosition = itemView.findViewById(R.id.tvNumber);
        this.tvStatus = itemView.findViewById(R.id.tvStatus);
        this.tvAmount = itemView.findViewById(R.id.tvMoney);
        this.tvRef = itemView.findViewById(R.id.tvRef);
        this.imEnter = itemView.findViewById(R.id.imShowDe);
        this.callback = callback;
    }

    public void bind(final HistoryModel model){

        int pos = getAdapterPosition() + 1;

        tvPosition.setText("#" + pos);
        tvStatus.setText(model.getStatus());
        tvRef.setText(model.getRef());
        tvAmount.setText("$ " + model.getAmount() + " MXN");

        imEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callbackSelected(model,getAdapterPosition());
            }
        });
    }
}
