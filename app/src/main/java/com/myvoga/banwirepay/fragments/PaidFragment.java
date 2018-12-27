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

public class PaidFragment extends Fragment {

    private Context context;
    private List<PayModel> items;
    private IItemSelected callback;

    private String title;
    private int page;

    private RecyclerView rvPaid;
    private PayAdapter adapter;

    public static PaidFragment newInstance(Context context, List<PayModel> items, int page, String title,IItemSelected callback){
        PaidFragment fragment = new PaidFragment();
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
        View view = inflater.inflate(R.layout.layout_paid, container, false);

        rvPaid = view.findViewById(R.id.rvPaid);

        rvPaid.setLayoutManager(new LinearLayoutManager(context));

        adapter = new PayAdapter(items,context,callback);

        rvPaid.setAdapter(adapter);

        rvPaid.addItemDecoration(new DividerDecode(
                getResources().getDimensionPixelSize(R.dimen.divider),1));

        return view;
    }
}
