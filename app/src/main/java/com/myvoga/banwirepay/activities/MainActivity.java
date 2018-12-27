package com.myvoga.banwirepay.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvoga.banwirepay.R;
import com.myvoga.banwirepay.adapters.ViewPagerAdapter;
import com.myvoga.banwirepay.dialogs.CardDialog;
import com.myvoga.banwirepay.fragments.CardsFragment;
import com.myvoga.banwirepay.fragments.NavigationDrawerFragment;
import com.myvoga.banwirepay.interfaces.IDialogCallback;
import com.myvoga.banwirepay.interfaces.IItemSelected;
import com.myvoga.banwirepay.interfaces.INavigateFragments;
import com.myvoga.banwirepay.models.PayModel;
import com.myvoga.banwirepay.utils.CardsPreferences;

import java.util.ArrayList;
import java.util.List;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import io.card.payment.DataEntryActivity;

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

    private static int SCAN_REQUEST = 1224;
    private static int KEY_REQUEST = 1226;

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
                CardsFragment fragment = CardsFragment.newInstance(this,this);
                showFragment(fragment);
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
                        keyNewCard();
                    }

                    @Override
                    public void cancel() {
                        scanNewCard();
                    }
                });
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager(),"CARD_DIALOG");
                break;
        }
    }

    private void keyNewCard(){
        Intent scanIntent = new Intent(this, DataEntryActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true);

        startActivityForResult(scanIntent, KEY_REQUEST);
    }

    private void scanNewCard(){
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_CAPTURED_CARD_IMAGE, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true);

        startActivityForResult(scanIntent, SCAN_REQUEST);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SCAN_REQUEST || requestCode == KEY_REQUEST){
            try{

                CardDialog cardDialog = null;

                CreditCard card = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                String name = card.cardholderName;

                if (name.isEmpty() || !name.contains(" ")){
                    cardDialog = CardDialog.newInstance(null,"Ingrese el nombre del titular correctamente","Aceptar");
                }else{
                    cardDialog = CardDialog.newInstance(null,"Tarjeta Registrada","Aceptar");
                }

                saveCard(card);

                cardDialog.setCancelable(true);
                cardDialog.show(getSupportFragmentManager(),"CARD_ERROR");

            }catch (Exception e){
                CardDialog cardDialog = CardDialog.newInstance(null,"Ha ocurrido un error","Aceptar");
                cardDialog.setCancelable(true);
                cardDialog.show(getSupportFragmentManager(),"CARD_ERROR");
            }
        }

        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void saveCard(CreditCard card){
        CardsPreferences.saveNewCard(this,card);
    }
}
