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

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.PayAdapter;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.models.PayModel;
import com.myvoga.banwirepay.utils.DividerDecode;

import java.util.List;

public class NoPaidFragment extends Fragment {

    private Context context;
    private List<PayModel> items;

    private String title;
    private int page;
    private IItemSelected callback;

    private RecyclerView rvNoPaid;
    private PayAdapter adapter;

    public static NoPaidFragment newInstance(Context context, List<PayModel> items, int page, String title, IItemSelected callback){
        NoPaidFragment fragment = new NoPaidFragment();

        fragment.context = context;
        fragment.items = items;
        fragment.page = page;
        fragment.title = title;
        fragment.callback = callback;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_no_paid, container, false);

        rvNoPaid = view.findViewById(R.id.rvNoPaid);

        rvNoPaid.setLayoutManager(new LinearLayoutManager(context));

        adapter = new PayAdapter(items,context,callback);

        rvNoPaid.setAdapter(adapter);

        rvNoPaid.addItemDecoration(new DividerDecode(
                getResources().getDimensionPixelSize(R.dimen.divider),2));

        return view;
    }
}
