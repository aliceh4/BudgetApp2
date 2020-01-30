package com.example.budgetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class Calendar extends AppCompatActivity {

    /* store amount of credits */
    private String credits;

    /* store amount of meals */
    private String meals;

    /* store monthly expense */
    private String monthlyExpense;

    /* store date selected */
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // gets intent from Balance
        Intent incoming = getIntent();
        credits = incoming.getStringExtra("credits");
        meals = incoming.getStringExtra("meals");
        monthlyExpense = incoming.getStringExtra("monthlyExpense");

        Button submitFutureExpense = findViewById(R.id.submitFutureExpense);

        CalendarView calendar = findViewById(R.id.calendarView);

        EditText futureExpenseAmount = findViewById(R.id.futureExpenseAmount);

        // store date and format to be shown on balance_information page
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = (i1 + 1) + "/" + i2 + "/" + i;
            }
        });

        submitFutureExpense.setOnClickListener(unused -> submitFutureExpenseClicked(futureExpenseAmount));
    }

    /**
     * Stores inputted amount/date in the intent to be shown on the balance_information page
     * @param futureExpenseAmount inputted amount by user to be spent (can be empty)
     */
    private void submitFutureExpenseClicked(EditText futureExpenseAmount) {
        Intent intent = new Intent(this, Balance.class);
        intent.putExtra("credits", credits);
        intent.putExtra("meals", meals);
        intent.putExtra("monthlyExpense", monthlyExpense);
        intent.putExtra("date", date);
        intent.putExtra("futureExpenseAmount", futureExpenseAmount.getText().toString());
        startActivity(intent);
        finish();
    }
}