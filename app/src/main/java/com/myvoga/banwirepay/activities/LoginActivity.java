package com.myvoga.banwirepay.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myvoga.banwirepay.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    TextView forget_pass;
    private Button btnEnter;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _context=this;

        this.initElements();

    }

    private void initElements() {
        forget_pass = findViewById(R.id.forget_pass);
        btnEnter = findViewById(R.id.btnEnter);
        forget_pass.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
    }

    private void doLogin(){
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_pass:
                Intent i = new Intent(_context, ForgetPassActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.btnEnter:
                this.doLogin();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
