package com.myvoga.banwirepay.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.CardsAdapter;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.interfaces.INavigateFragments;
import com.myvoga.banwirepay.utils.CardsPreferences;
import com.myvoga.banwirepay.utils.DividerDecode;

import java.util.List;

import io.card.payment.CreditCard;

public class CardsFragment extends Fragment implements View.OnClickListener, IItemSelected {


    private Context context;
    private RecyclerView rvCards;
    private ImageView ivBack;
    private INavigateFragments navigationCallback;

    private List<CreditCard> cardList;
    private CardsAdapter adapter;

    public static CardsFragment newInstance(Context context, INavigateFragments navigationCallback){
        CardsFragment fragment = new CardsFragment();
        fragment.context = context;
        fragment.navigationCallback = navigationCallback;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        rvCards = view.findViewById(R.id.rvCards);
        ivBack = view.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);

        rvCards.setLayoutManager(new LinearLayoutManager(context));

        rvCards.addItemDecoration(new DividerDecode(
                getResources().getDimensionPixelSize(R.dimen.divider),1));

        this.createAdapter();

        return view;
    }

    private void createAdapter(){
        cardList = CardsPreferences.getCards(context);

        if (cardList != null && cardList.size() > 0){
            adapter = new CardsAdapter(cardList,context,this);
            rvCards.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                navigationCallback.popFragment();
                break;
        }
    }

    @Override
    public void callbackSelected(Object model, int position) {
        CreditCard card = (CreditCard) model;
        CardsPreferences.deleteCard(context,card.cardNumber);

        cardList.remove(position);

        adapter.notifyDataSetChanged();
    }
}
