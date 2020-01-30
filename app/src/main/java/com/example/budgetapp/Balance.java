package com.example.budgetapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public final class Balance extends AppCompatActivity {

    /** number of meals user has left */
    private String meals;

    /** amount of credits user has left */
    private String credits;

    /** amount of weekly expense left */
    private String weeklyExpense;

    /** stores the date */
    private String date;

    /** stores the future expense */
    private String futureExpenseAmount;

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_information);

        // gets the intent from MainActivity
        Intent intent = getIntent();

        // sets the UI to match previously selected data
        TextView mealBalance = findViewById(R.id.mealBalance);
        mealBalance.setText(intent.getStringExtra("meals"));
        meals = intent.getStringExtra("meals");

        TextView creditBalance = findViewById(R.id.creditBalance);
        creditBalance.setText(intent.getStringExtra("credits"));
        credits = intent.getStringExtra("credits");

        TextView monthlyBalance = findViewById(R.id.weeklyBalance);
        monthlyBalance.setText(intent.getStringExtra("monthlyExpense"));
        weeklyExpense = intent.getStringExtra("monthlyExpense");

        // adds date and expense planned
        TextView futureExpenseText = findViewById(R.id.futureExpenseTextView);
        futureExpenseAmount = intent.getStringExtra("futureExpenseAmount");
        date = intent.getStringExtra("date");
        if (date != null && date.length() > 0) {
            if (futureExpenseAmount != null) {
                subtractFutureExpense();
                futureExpenseText.setText(date + ":  $" + futureExpenseAmount);
            } else {
                futureExpenseText.setText(date + ":  " + "$0");
            }
        }

        // creates buttons and listeners
        Button subtractMeal = findViewById(R.id.mealSpent);
        Button subtractCredit = findViewById(R.id.subtractCredits);
        Button subtractMonthly = findViewById(R.id.subtractWeekly);
        Button futureExpense = findViewById(R.id.futureExpense);
        subtractMeal.setOnClickListener(unused -> subtractMealClicked());
        subtractCredit.setOnClickListener(unused -> subtractCreditClicked());
        subtractMonthly.setOnClickListener(unused -> subtractWeeklyClicked());
        futureExpense.setOnClickListener(unused -> futureExpenseClicked());
    }

    // Runs when "-1 Meal" button is clicked and reduces number by 1
    private void subtractMealClicked() {
        TextView mealBalance = findViewById(R.id.mealBalance);
        int ogMealBalance = Integer.parseInt(mealBalance.getText().toString());
        // shows alert dialog if there are no more meals left and button is clicked
        if (ogMealBalance == 0) {
            showMealAlertDialog();
        } else {
            String afterClicked = String.valueOf(ogMealBalance - 1);
            meals = afterClicked;
            mealBalance.setText(afterClicked);
        }
    }

    // Subtracts inputted amount (if any) from credits when subtractCredits is clicked
    private void subtractCreditClicked() {
        TextView creditBalance = findViewById(R.id.creditBalance);
        EditText creditsSpent = findViewById(R.id.creditsSpent);
        int subtractCredit = Integer.parseInt(creditsSpent.getText().toString());
        int ogCredit = Integer.parseInt(creditBalance.getText().toString());
        int result = ogCredit - subtractCredit;
        // checks if needs to show alert dialog
        if (result >= 0 && ogCredit > 0) {
            creditBalance.setText(String.valueOf(result));
            credits = String.valueOf(result);
            creditsSpent.setText("");
        } else {
            showAmountAlertDialog();
            creditsSpent.setText("");
        }
    }

    // subtracts inputted expense when button clicked
    private void subtractWeeklyClicked() {
        TextView creditBalance = findViewById(R.id.weeklyBalance);
        EditText creditsSpent = findViewById(R.id.weeklySpent);
        int subtract = Integer.parseInt(creditsSpent.getText().toString());
        int og = Integer.parseInt(creditBalance.getText().toString());
        int after = og - subtract;
        if (after >= 0 && og > 0) {
            weeklyExpense = String.valueOf(after);
            creditBalance.setText(String.valueOf(after));
            creditsSpent.setText("");
        } else {
            showAmountAlertDialog();
            creditsSpent.setText("");
        }
    }

    // Future expense subtracted
    private void subtractFutureExpense() {
        TextView creditBalance = findViewById(R.id.weeklyBalance);
        int subtract =  Integer.parseInt(futureExpenseAmount);
        int og = Integer.parseInt(weeklyExpense);
        int after = og - subtract;
        if (after >= 0 && og > 0) {
            weeklyExpense = String.valueOf(after);
            creditBalance.setText(String.valueOf(after));
        } else {
            showAmountAlertDialog();
        }
    }

    // Alert Dialog for credits and expenses
    AlertDialog myDialog;
    public void showAmountAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Whoops");
        builder.setMessage("This input is too much! Consider getting something cheaper.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDialog.dismiss();
            }
        });
        myDialog = builder.show();
        return;

    }

    // Alert dialog for meals
    AlertDialog second;
    public void showMealAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Uh oh...");
        builder.setMessage("You have no more meal swipes.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                second.dismiss();
            }
        });
        second = builder.show();
        return;

    }

    // when button is clicked, brings you to calendar page and stores previous data in intent
    private void futureExpenseClicked() {
        Intent intent = new Intent(this, Calendar.class);
        // in the future, want to use credits, meals, and monthlyExpense in calendar
        intent.putExtra("credits", credits);
        intent.putExtra("meals", meals);
        intent.putExtra("monthlyExpense", weeklyExpense);
        startActivity(intent);
        finish();
    }
}
