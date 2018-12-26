package com.myvoga.banwirepay.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.fragments.NoPaidFragment;
import com.myvoga.banwirepay.fragments.PaidFragment;
import com.myvoga.banwirepay.models.PayModel;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;

    private Context context;
    private List<PayModel> items;

    public ViewPagerAdapter(FragmentManager fm, Context context, List<PayModel> items) {
        super(fm);
        this.context = context;
        this.items = items;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return PaidFragment.newInstance(context,items,0,context.getResources().getString(R.string.pf_title_paid));
            case 1:
                return NoPaidFragment.newInstance(context,items,1,context.getResources().getString(R.string.pf_title_nopaid));
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
