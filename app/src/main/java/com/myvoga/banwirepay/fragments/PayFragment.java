package com.myvoga.banwirepay.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.ViewPagerAdapter;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.interfaces.INavigateFragments;
import com.myvoga.banwirepay.models.PayModel;

import java.util.List;

public class PayFragment extends Fragment implements View.OnClickListener, IItemSelected {

    private Context context;
    private List<PayModel> itemsPay;
    private INavigateFragments callbackNav;

    private ViewPager viewPager;
    private ImageView ivBack;

    private ViewPagerAdapter viewPagerAdapter;

    public static PayFragment newInstance(Context context, List<PayModel> itemsPay, INavigateFragments callbackNav){

        PayFragment fragment = new PayFragment();
        fragment.context = context;
        fragment.itemsPay = itemsPay;
        fragment.callbackNav = callbackNav;

        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay, container, false);

        viewPager = view.findViewById(R.id.vpPay);
        ivBack = view.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);

        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),context,itemsPay,this);

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.setOffscreenPageLimit(2);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                callbackNav.popFragment();
                break;
        }
    }

    @Override
    public void callbackSelected(Object model, int position) {

    }
}
