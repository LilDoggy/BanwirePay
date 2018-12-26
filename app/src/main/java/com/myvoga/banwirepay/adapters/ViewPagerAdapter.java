package com.myvoga.banwirepay.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.fragments.NoPaidFragment;
import com.myvoga.banwirepay.fragments.PaidFragment;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.models.PayModel;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;

    private Context context;
    private List<PayModel> items;
    private IItemSelected callback;

    private List<PayModel> paidItems;
    private List<PayModel> noPaidItems;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<PayModel> items, IItemSelected callback) {
        super(fm);
        this.context = context;
        this.items = items;
        this.callback = callback;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                paidItems = new ArrayList<>();
                for (PayModel model : items){
                    if (model.isPaid())
                        paidItems.add(model);
                }
                return PaidFragment.newInstance(context,paidItems,0,context.getResources().getString(R.string.pf_title_paid),callback);
            case 1:
                noPaidItems = new ArrayList<>();
                for (PayModel model : items){
                    if (!model.isPaid())
                        noPaidItems.add(model);
                }
                return NoPaidFragment.newInstance(context,noPaidItems,1,context.getResources().getString(R.string.pf_title_nopaid),callback);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getString(R.string.pf_title_paid);
            case 1:
                return context.getResources().getString(R.string.pf_title_nopaid);
            default:
                return "Page";
        }
    }
}
