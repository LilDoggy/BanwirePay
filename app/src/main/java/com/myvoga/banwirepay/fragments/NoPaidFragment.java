package com.myvoga.banwirepay.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myvoga.banwirepay.models.PayModel;

import java.util.List;

public class NoPaidFragment extends Fragment {

    private Context context;
    private List<PayModel> items;

    private String title;
    private int page;

    public static NoPaidFragment newInstance(Context context, List<PayModel> items, int page, String title){
        NoPaidFragment fragment = new NoPaidFragment();

        fragment.context = context;
        fragment.items = items;
        fragment.page = page;
        fragment.title = title;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
