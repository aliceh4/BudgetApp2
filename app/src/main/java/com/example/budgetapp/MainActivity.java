package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making sure the first thing that appears when app is opened is this page
        setContentView(R.layout.home_page);

        // Radiogroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText monthlyExpense = findViewById(R.id.monthlyExpense);
        Button expenseButton = findViewById(R.id.expenseButton);
        // Listener for when a radiobutton is selected
        expenseButton.setOnClickListener(unused -> createMealBalanceClicked(radioGroup, monthlyExpense));
    }

    /**
     * Stores data from selected radiobutton and inputted number into an intent as extras
     * and starts the next activity (balance page). Also begins the process of planning
     * for future expenses.
     * @param radioGroup the selection of radiobuttons for mealplan
     * @param weeklyExpense the EditText where the user can write their weekly budget
     */
    private void createMealBalanceClicked(RadioGroup radioGroup, EditText weeklyExpense) {
        Intent intent = new Intent(this, Balance.class);
        // Checking which button is checked and adding the information into the intent
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
        // checking what number is inputted, if any, and storing in the intent
        if (weeklyExpense.getText().toString().equals("")) {
            intent.putExtra("monthlyExpense", "0");
        } else {
            intent.putExtra("monthlyExpense", weeklyExpense.getText().toString());
        }

        // Storing empty extras in the intent for the date and future expense
        intent.putExtra("date", "");
        intent.putExtra("futureExpenseAmount", "");

        startActivity(intent);
    }

}