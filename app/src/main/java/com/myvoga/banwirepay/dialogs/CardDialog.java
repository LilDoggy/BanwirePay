package com.myvoga.banwirepay.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.interfaces.IDialogCallback;

public class CardDialog extends DialogFragment implements View.OnClickListener {

    private IDialogCallback callback;

    private TextView tvManual;
    private TextView tvScanner;

    public CardDialog(){}

    public static CardDialog newInstance(IDialogCallback callback){
        CardDialog dialog = new CardDialog();
        dialog.callback = callback;

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_card, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        tvManual = view.findViewById(R.id.tvManual);
        tvScanner = view.findViewById(R.id.tvScanner);

        tvManual.setOnClickListener(this);
        tvScanner.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvManual:
                dismiss();
                callback.accept();
                break;
            case R.id.tvScanner:
                dismiss();
                callback.cancel();
                break;
        }

    }
}
