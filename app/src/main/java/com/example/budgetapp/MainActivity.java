package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealplan_planning);

        Button mealPlanOne = findViewById(R.id.twelveTen);
        Button mealPlanTwo = findViewById(R.id.tenFortyFive);
        Button mealPlanThree = findViewById(R.id.meals);
        Button mealPlanFour = findViewById(R.id.credits);

        mealPlanOne.setOnClickListener(unused -> createMealBalanceClicked());
        mealPlanTwo.setOnClickListener(unused -> createMealBalanceClicked());
        mealPlanThree.setOnClickListener(unused -> createMealBalanceClicked());
        mealPlanFour.setOnClickListener(unused -> createMealBalanceClicked());
    }

    private void createMealBalanceClicked() {
        Intent intent = new Intent(this, Balance.class);
        startActivity(intent);
    }

}