package com.myvoga.banwirepay.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myvoga.banwirepay.R;

public class ForgetPassActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        ((ImageView) findViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.call)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "+52 (55) 1579 9155";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

    }
}
