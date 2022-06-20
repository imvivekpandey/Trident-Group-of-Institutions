package com.example.tridentgroupofinstitutions;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class EnterPhoneActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    String phoneNumber;

    public EnterPhoneActivity() {

    }



    FirebaseAuth mAuth;
    private static final int CREDENTIAL_PICKER_REQUEST =120 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone);
        getSupportActionBar().hide();

        t1=findViewById(R.id.otp1);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button)findViewById(R.id.bu);


        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();


        PendingIntent intent = Credentials.getClient(EnterPhoneActivity.this).getHintPickerIntent(hintRequest);
        try
        {
            startIntentSenderForResult(intent.getIntentSender(), CREDENTIAL_PICKER_REQUEST, null, 0, 0, 0,new Bundle());
        }
        catch (IntentSender.SendIntentException e)
        {
            e.printStackTrace();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(t1.getText().toString())){
                    Toast.makeText(EnterPhoneActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
                else if(t1.getText().toString().replace(" ","").length()!=10){
                    Toast.makeText(EnterPhoneActivity.this, "Enter A Valid Number...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(EnterPhoneActivity.this,OTPreciveActivity.class);
                    intent.putExtra("number",ccp.getFullNumberWithPlus().replace(" ",""));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == RESULT_OK)
        {
            com.google.android.gms.auth.api.credentials.Credential credentials = data.getParcelableExtra(Credential.EXTRA_KEY);


           phoneNumber = credentials.getId().substring(3);
            t1.setText(phoneNumber);


        }
        else if (requestCode == CREDENTIAL_PICKER_REQUEST && resultCode == CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE)
        {
            // *** No phone numbers available ***
            Toast.makeText(EnterPhoneActivity.this, "No phone numbers found", Toast.LENGTH_LONG).show();
        }


    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    }

