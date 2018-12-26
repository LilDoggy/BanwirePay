package com.myvoga.banwirepay.interfaces;

import android.support.v4.app.Fragment;

public interface INavigateFragments {
    void pushFragment(Fragment fragment);
    void popFragment();
    void replaceLastFragment(Fragment fragment);
}
