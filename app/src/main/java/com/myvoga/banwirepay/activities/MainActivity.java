package com.myvoga.banwirepay.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.HistoryAdapter;
import com.myvoga.banwirepay.fragments.NavigationDrawerFragment;
import com.myvoga.banwirepay.fragments.PayFragment;
import com.myvoga.banwirepay.interfaces.IHistoryCallback;
import com.myvoga.banwirepay.interfaces.INavigateFragments;
import com.myvoga.banwirepay.models.HistoryModel;
import com.myvoga.banwirepay.models.PayModel;
import com.myvoga.banwirepay.utils.DividerDecode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener,
        INavigateFragments{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ImageView ivDrawer;
//    private EditText etSearch;
    private RecyclerView rvHistory;
    private HistoryAdapter adapter;
    private List<HistoryModel> items;
    private Context _context;
    private TextView btnShowIndicators;
    private TextView btnTrans;

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
//        etSearch = findViewById(R.id.etSearchTran);
        rvHistory = findViewById(R.id.rvHistory);
        btnShowIndicators = findViewById(R.id.btnShowIndicators);
        btnTrans = findViewById(R.id.btnTrans);


        ivDrawer.setOnClickListener(this);
        btnShowIndicators.setOnClickListener(this);
        btnTrans.setOnClickListener(this);

//        etSearch.setHint("\uD83D\uDD0D " + getResources().getString(R.string.hint_search_transaction));

        this.createAdapter();
    }

    private void createAdapter(){
        items = new ArrayList<>();
        items.add(new HistoryModel("Pagada","ref: 2123","100"));
        items.add(new HistoryModel("Rechazada","ref: 2123","600"));
        items.add(new HistoryModel("Pagada","ref: 2123","1015"));
        items.add(new HistoryModel("Rechazada","ref: 2123","115"));
        items.add(new HistoryModel("Rechazada","ref: 2123","115"));

        adapter = new HistoryAdapter(items, new IHistoryCallback() {
            @Override
            public void callbackSelected(Object model, int position) {
                Toast.makeText(MainActivity.this, "Click en Item: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);

        rvHistory.addItemDecoration(new DividerDecode(
                getResources().getDimensionPixelSize(R.dimen.divider),2));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments+

        Intent i;
        List<HistoryModel> items = new ArrayList<>();

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
                this.showPayFragment();
                break;
            case 2:
                Toast.makeText(this, "Instrumentos", Toast.LENGTH_SHORT).show();
                break;
            case 3:
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
            case R.id.btnShowIndicators:
//                this.showIndicators();
                break;
            case R.id.btnTrans:
                onNavigationDrawerItemSelected(4);
                break;
        }
    }

    private void showPayFragment(){

        List<PayModel> modelList = new ArrayList<>();

        modelList.add(new PayModel("54","Servicio de Luz","500","098976","03/11/2018",true));
        modelList.add(new PayModel("53","Nomina","1500","863436","03/11/2018",true));
        modelList.add(new PayModel("52","Concepto 3","200","213976","03/11/2018",true));
        modelList.add(new PayModel("51","Concepto 4","100","241526","03/11/2018",true));

        modelList.add(new PayModel("54","Concepto 1","500","42526622","03/11/2018",false));
        modelList.add(new PayModel("54","Concepto 2","1500","22737383","03/11/2018",false));

        PayFragment fragment = PayFragment.newInstance(this,modelList,this);
        showFragment(fragment);
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
}
