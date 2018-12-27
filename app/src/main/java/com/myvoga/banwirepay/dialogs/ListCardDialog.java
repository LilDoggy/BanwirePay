package com.myvoga.banwirepay.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.CardsAdapter;
import com.myvoga.banwirepay.utils.DividerDecode;

import java.util.List;

import io.card.payment.CreditCard;

public class ListCardDialog extends DialogFragment {

    private Context context;
    private List<CreditCard> items;

    private RecyclerView rvCards;
    private CardsAdapter adapter;

    public ListCardDialog(){}

    public static ListCardDialog newInstance(Context context, List<CreditCard> items){
        ListCardDialog dialog = new ListCardDialog();
        dialog.context = context;
        dialog.items = items;

        return dialog;

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width,height);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.back_dialog)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_list_card, container, false);

        rvCards = view.findViewById(R.id.rvCards);

        rvCards.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CardsAdapter(items,context,null);

        rvCards.setAdapter(adapter);

        rvCards.addItemDecoration(new DividerDecode(
                getResources().getDimensionPixelSize(R.dimen.divider),1));

        return view;
    }
}
