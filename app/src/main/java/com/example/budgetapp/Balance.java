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

    /**
     * storing this in a variable so I could pass it later.
     */
    private String meals;

    /**
     * storing this in a variable so I could pass it later.
     */
    private String credits;

    /**
     * storing this in a variable so I could pass it later.
     */
    private String monthlyExpense;

    /** i think I just like private instance variables. */
    private String date;

    private String futureExpenseAmount;

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Intent intent = getIntent();

        TextView mealBalance = findViewById(R.id.mealBalance);
        mealBalance.setText(intent.getStringExtra("meals"));
        meals = intent.getStringExtra("meals");

        TextView creditBalance = findViewById(R.id.creditBalance);
        creditBalance.setText(intent.getStringExtra("credits"));
        credits = intent.getStringExtra("credits");

        TextView monthlyBalance = findViewById(R.id.monthlyBalance);
        monthlyBalance.setText(intent.getStringExtra("monthlyExpense"));
        monthlyExpense = intent.getStringExtra("monthlyExpense");

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

        Button subtractMeal = findViewById(R.id.mealSpent);
        Button subtractCredit = findViewById(R.id.subtractCredits);
        Button subtractMonthly = findViewById(R.id.subtractMonthly);

        Button futureExpense = findViewById(R.id.futureExpense);

        subtractMeal.setOnClickListener(unused -> subtractMealClicked());
        subtractCredit.setOnClickListener(unused -> subtractCreditClicked());
        subtractMonthly.setOnClickListener(unused -> subtractMonthlyClicked());
        futureExpense.setOnClickListener(unused -> futureExpenseClicked());
    }

    private void subtractMealClicked() {
        TextView mealBalance = findViewById(R.id.mealBalance);
        int og = Integer.parseInt(mealBalance.getText().toString());
        if (og == 0) {
            showSecondAlertDialog();
        } else {
            String after = String.valueOf(og - 1);
            meals = after;
            mealBalance.setText(after);
        }
    }

    private void subtractCreditClicked() {
        TextView creditBalance = findViewById(R.id.creditBalance);
        EditText creditsSpent = findViewById(R.id.creditsSpent);
        int subtract = Integer.parseInt(creditsSpent.getText().toString());
        int og = Integer.parseInt(creditBalance.getText().toString());
        int after = og - subtract;
        if (after >= 0 && og > 0) {
            creditBalance.setText(String.valueOf(after));
            credits = String.valueOf(after);
            creditsSpent.setText("");
        } else {
            showAlertDialog();
            creditsSpent.setText("");
        }
    }

    private void subtractMonthlyClicked() {
        TextView creditBalance = findViewById(R.id.monthlyBalance);
        EditText creditsSpent = findViewById(R.id.monthlySpent);
        int subtract = Integer.parseInt(creditsSpent.getText().toString());
        int og = Integer.parseInt(creditBalance.getText().toString());
        int after = og - subtract;
        if (after >= 0 && og > 0) {
            monthlyExpense = String.valueOf(after);
            creditBalance.setText(String.valueOf(after));
            creditsSpent.setText("");
        } else {
            showAlertDialog();
            creditsSpent.setText("");
        }
    }

    private void subtractFutureExpense() {
        TextView creditBalance = findViewById(R.id.monthlyBalance);
        int subtract =  Integer.parseInt(futureExpenseAmount);
        int og = Integer.parseInt(monthlyExpense);
        int after = og - subtract;
        if (after >= 0 && og > 0) {
            monthlyExpense = String.valueOf(after);
            creditBalance.setText(String.valueOf(after));
        } else {
            showAlertDialog();
        }
    }

    AlertDialog myDialog;
    public void showAlertDialog() {
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

    AlertDialog second;
    public void showSecondAlertDialog() {
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

    private void futureExpenseClicked() {
        Intent intent = new Intent(this, Calendar.class);
        intent.putExtra("credits", credits);
        intent.putExtra("meals", meals);
        intent.putExtra("monthlyExpense", monthlyExpense);
        startActivity(intent);
        finish();
    }
}
