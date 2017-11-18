package com.chargebee.android.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chargebee.Environment;
import com.chargebee.checkout.Chargebee;
import com.chargebee.checkout.Chargebee.OptionsBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.btn_login)
    Button _login;
    @InjectView(R.id.link_signup)
    TextView _signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Chargebee.init(OptionsBuilder.b());
        Environment.configure("f51adba4", "test___dev__WIAMf0uomUcuwG5gtXcuTrVtJ6cdZOMo8cus");
        _signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }


}
