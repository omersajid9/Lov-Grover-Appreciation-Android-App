package com.sajidomer.ardenwarnerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sajidomer.ardenwarnerapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity
{

    private ActivityHomeBinding binding;

    public static final int FROM_HOME_ACTIVITY = 1;

    private String uriWikipedia = "https://en.wikipedia.org/wiki/Lov_Grover";
    private String uriBirthLocation = "geo:0,0?q=Meerut,+Uttar+Pradesh,+India";
    private String uriCurLocation = "geo:0,0?q=Bell+Laboratories,+NJ";


    private View.OnClickListener button_onClickListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view)
        {
            switch(view.getId())
            {
                case R.id.button_Bio:
                    Intent bioIntent = new Intent(HomeActivity.this, BiographyActivity.class);
                    startActivity(bioIntent);
                    break;
                case R.id.button_Info:
                    Intent wikiIntent = new Intent();
                    wikiIntent.setAction(Intent.ACTION_VIEW);
                    wikiIntent.setData(Uri.parse(uriWikipedia));
                    if(wikiIntent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(wikiIntent);
                    }
                    break;
                case R.id.button_Donate:
                    Intent donateIntent = new Intent(HomeActivity.this, DonateActivity.class);
                    startActivityForResult(donateIntent, FROM_HOME_ACTIVITY);
                    break;
            }
        }
    };

    private View.OnClickListener location_onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.textView_Born_location:
                    Intent birthMapIntent = new Intent();
                    birthMapIntent.setAction(Intent.ACTION_VIEW);
                    birthMapIntent.setData(Uri.parse(uriBirthLocation));
                    if(birthMapIntent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(birthMapIntent);
                    }
                    break;
                case R.id.textView_Location:
                    Intent curLocationIntent = new Intent();
                    curLocationIntent.setAction(Intent.ACTION_VIEW);
                    curLocationIntent.setData(Uri.parse(uriCurLocation));
                    if(curLocationIntent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(curLocationIntent);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textViewBornLocation.setOnClickListener(location_onClickListener);
        binding.textViewLocation.setOnClickListener(location_onClickListener);

        binding.buttonBio.setOnClickListener(button_onClickListener);
        binding.buttonInfo.setOnClickListener(button_onClickListener);
        binding.buttonDonate.setOnClickListener(button_onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("Omer", "Before request");

        if(requestCode == FROM_HOME_ACTIVITY && resultCode == Activity.RESULT_OK)
        {
            Log.d("Omer", "Enter request");

            String fullName = data.getStringExtra(DonateActivity.EXTRA_FULL_NAME);
            String phone = data.getStringExtra(DonateActivity.EXTRA_PHONE);
            String creditCard = data.getStringExtra(DonateActivity.EXTRA_CREDIT_CARD);
            int cvc = data.getIntExtra(DonateActivity.EXTRA_CVC, -1);
            float amount = data.getFloatExtra(DonateActivity.EXTRA_AMOUNT, -1);
            boolean receipt = data.getBooleanExtra(DonateActivity.EXTRA_RECEIPT, false);

            Log.d("Omer", String.valueOf(fullName));

            if(receipt)
            {
                String message = "Thank you " + fullName + " for your donation of $" + amount + " using card number ending in " + creditCard.substring(creditCard.length()-4, creditCard.length());
                Intent messageIntent = new Intent();
                messageIntent.setAction(Intent.ACTION_SENDTO);
                messageIntent.setData(Uri.parse("sms:"+phone));
                messageIntent.putExtra("sms_body", message);
                Log.d("Omer", message);
                if(messageIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(messageIntent);
                }
            }
        }
    }
}