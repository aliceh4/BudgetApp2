package com.example.budgetapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public final class Balance extends AppCompatActivity {

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button subtractMeal = findViewById(R.id.mealSpent);
        Button subtractCredit = findViewById(R.id.subtractCredits);

        subtractMeal.setOnClickListener(unused -> subtractMealClicked());
        subtractCredit.setOnClickListener(unused -> subtractCreditClicked());
    }

    private void subtractMealClicked() {
        TextView mealBalance = findViewById(R.id.mealBalance);
        mealBalance.setText("100");
    }

    private void subtractCreditClicked() {
        TextView creditBalance = findViewById(R.id.creditBalance);
        creditBalance.setText("100");
    }
}