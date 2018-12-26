package com.myvoga.banwirepay.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.ViewPagerAdapter;
import com.myvoga.banwirepay.dialogs.CardDialog;
import com.myvoga.banwirepay.fragments.NavigationDrawerFragment;
import com.myvoga.banwirepay.interfaces.IDialogCallback;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.interfaces.INavigateFragments;
import com.myvoga.banwirepay.models.PayModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener,
        INavigateFragments, IItemSelected {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ImageView ivDrawer;
//    private EditText etSearch;
    private Context _context;
    private TextView btnLoadCards;

    private List<PayModel> modelList;
    private INavigateFragments callbackNav;

    private ViewPager viewPager;
    private ImageView ivBack;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _context=this;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ivDrawer = findViewById(R.id.ivDrawer);
        viewPager = findViewById(R.id.vpPay);
        btnLoadCards = findViewById(R.id.btnLoadCards);

        btnLoadCards.setOnClickListener(this);
        ivDrawer.setOnClickListener(this);

        this.createAdapter();
    }

    private void createAdapter(){
        modelList = new ArrayList<>();

        modelList.add(new PayModel("54","Servicio de Luz","500","098976","03/11/2018",true));
        modelList.add(new PayModel("53","Nomina","1500","863436","03/11/2018",true));
        modelList.add(new PayModel("52","Concepto 3","200","213976","03/11/2018",true));
        modelList.add(new PayModel("51","Concepto 4","100","241526","03/11/2018",true));

        modelList.add(new PayModel("54","Concepto 1","500","42526622","03/11/2018",false));
        modelList.add(new PayModel("54","Concepto 2","1500","22737383","03/11/2018",false));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),this,modelList,this);

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments+

        Intent i;

        switch (position){
            case 0:
                for (Fragment fragment:getSupportFragmentManager().getFragments()) {
//                    if (fragment instanceof NavigationDrawerFragment) {
//                        continue;
//                    }
//                    else {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                    }
                }
                break;
            case 1:
                Toast.makeText(this, "Tarjetas", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                i = new Intent(_context, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivDrawer:
                if (mNavigationDrawerFragment.isDrawerOpen())
                    mNavigationDrawerFragment.closeDrawer();
                else
                    mNavigationDrawerFragment.openDrawer();
                break;
            case R.id.btnLoadCards:
                CardDialog dialog = CardDialog.newInstance(new IDialogCallback() {
                    @Override
                    public void accept() {

                    }

                    @Override
                    public void cancel() {

                    }
                });
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(),"CARD_DIALOG");
                break;
        }
    }

    //Interfaces to Navigate between Activity and Fragments

    @Override
    public void pushFragment(Fragment fragment) {
        showFragment(fragment);
    }

    @Override
    public void popFragment() {
        pop();
    }

    @Override
    public void replaceLastFragment(Fragment fragment) {
        pop();
        showFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        customBack();
    }

    private void showFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.drawer_layout, fragment);
        ft.addToBackStack(fragment.getClass().getName());
        ft.commit();
    }

    private void customBack(){
        int countFrag = getSupportFragmentManager().getBackStackEntryCount();
        if (countFrag > 0)
            pop();
    }

    private void pop(){
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void callbackSelected(Object model, int position) {

    }
}
