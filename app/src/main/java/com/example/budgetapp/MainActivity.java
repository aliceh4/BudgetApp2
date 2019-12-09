package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealplan_planning);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText monthlyExpense = findViewById(R.id.monthlyExpense);

        RadioButton mealPlanOne = radioGroup.findViewById(R.id.twelveFifteen);
        RadioButton mealPlanTwo = radioGroup.findViewById(R.id.tenFortyFive);
        RadioButton mealPlanThree = radioGroup.findViewById(R.id.meals);
        RadioButton mealPlanFour = radioGroup.findViewById(R.id.credits);

        Button expenseButton = findViewById(R.id.expenseButton);

        expenseButton.setOnClickListener(unused -> createMealBalanceClicked(radioGroup, monthlyExpense));
    }

    private void createMealBalanceClicked(RadioGroup radioGroup, EditText monthlyExpense) {
        Intent intent = new Intent(this, Balance.class);
        if (radioGroup.getCheckedRadioButtonId() == R.id.twelveFifteen) {
            intent.putExtra("meals", "12");
            intent.putExtra("credits", "15");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.tenFortyFive) {
            intent.putExtra("meals", "10");
            intent.putExtra("credits", "45");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.meals) {
            intent.putExtra("meals", "47");
            intent.putExtra("credits", "0");
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.credits) {
            intent.putExtra("meals", "0");
            intent.putExtra("credits", "130");
        } else {
            intent.putExtra("meals", "0");
            intent.putExtra("credits", "0");
        }
        if (monthlyExpense.getText().toString().equals("")) {
            intent.putExtra("monthlyExpense", "0");
        } else {
            intent.putExtra("monthlyExpense", monthlyExpense.getText().toString());
        }

        intent.putExtra("date", "");
        intent.putExtra("futureExpenseAmount", "");

        startActivity(intent);
    }

}