package com.sajidomer.ardenwarnerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sajidomer.ardenwarnerapp.databinding.ActivityDonateBinding;


public class DonateActivity extends AppCompatActivity {

    private ActivityDonateBinding binding;

    public static final String EXTRA_FULL_NAME = "com.sajidomer.ardenwarnerapp.EXTRA_FULL_NAME";
    public static final String EXTRA_PHONE = "com.sajidomer.ardenwarnerapp.EXTRA_PHONE";
    public static final String EXTRA_CREDIT_CARD = "com.sajidomer.ardenwarnerapp.EXTRA_CREDIT_CARD";
    public static final String EXTRA_CVC = "com.sajidomer.ardenwarnerapp.EXTRA_CVC";
    public static final String EXTRA_AMOUNT = "com.sajidomer.ardenwarnerapp.EXTRA_AMOUNT";
    public static final String EXTRA_RECEIPT = "com.sajidomer.ardenwarnerapp.EXTRA_RECEIPT";

    private AlertDialog.Builder myBuilder;

    private View.OnClickListener submit_OnClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {



            if(!lengthEqual(binding.editTextFname, 0))
            {
                if(lengthEqual(binding.editTextPhone, 8))
                {
                    if(binding.editTextCreditcard.getText().toString().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}"))
                    {
                        if(lengthEqual(binding.editTextCvc, 3))
                        {
                            if(!lengthEqual(binding.editTextAmount, 0))
                            {
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra(EXTRA_FULL_NAME, binding.editTextFname.getText().toString());
                                returnIntent.putExtra(EXTRA_PHONE, binding.editTextPhone.getText().toString());
                                returnIntent.putExtra(EXTRA_CREDIT_CARD, binding.editTextCreditcard.getText().toString());
                                returnIntent.putExtra(EXTRA_CVC, Integer.valueOf(binding.editTextCvc.getText().toString()));
                                returnIntent.putExtra(EXTRA_AMOUNT, Float.valueOf(binding.editTextAmount.getText().toString()));
                                returnIntent.putExtra(EXTRA_RECEIPT, binding.switchReceipt.isChecked());
                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();
                            }
                            else
                            {
                                AlertDialog myDialog = createAlertBuilder("Donation amount not found", "Please enter the amount you want to donate", "Ok", positiveDoNothingClick());
                                myDialog.show();
                            }
                        }
                        else
                        {
                            AlertDialog myDialog = createAlertBuilder("Incorrect CVC format!", "Please enter your correct 3 digit CVC", "Ok", positiveDoNothingClick());
                            myDialog.show();
                        }
                    }
                    else
                    {
                        AlertDialog myDialog = createAlertBuilder("Incorrect Credit Card format!", "Kindly enter your credit card information in the following format: XXXX-XXXX-XXXX-XXXX", "Ok", positiveDoNothingClick());
                        myDialog.show();
                    }
                }
                else
                {
                    AlertDialog myDialog = createAlertBuilder("Phone number in incorrect manner", "Please enter your 8 digit phone number", "Ok", positiveDoNothingClick());
                    myDialog.show();
                }
            }
            else
            {
                AlertDialog myDialog = createAlertBuilder("Full name not found", "Please enter your full name", "Ok", positiveDoNothingClick());
                myDialog.show();
            };

        }
    };

    private DialogInterface.OnClickListener positiveDoNothingClick()
    {
        return new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
            }
        };
    }

    private AlertDialog createAlertBuilder(String title, String message, String positiveMessage, DialogInterface.OnClickListener alertOnClickListener)
    {
        myBuilder = new AlertDialog.Builder(DonateActivity.this);
        myBuilder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveMessage, alertOnClickListener);
        return myBuilder.create();
    };

    private boolean lengthEqual(EditText text, int length)
    {
        return text.getText().toString().length() == length;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonDonate1.setOnClickListener(submit_OnClickListener);

    }
}