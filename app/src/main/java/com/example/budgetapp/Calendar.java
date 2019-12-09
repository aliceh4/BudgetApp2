package com.example.budgetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

public class Calendar extends AppCompatActivity {

    private String credits;
    private String meals;
    private String monthlyExpense;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent incoming = getIntent();
        credits = incoming.getStringExtra("credits");
        meals = incoming.getStringExtra("meals");
        monthlyExpense = incoming.getStringExtra("monthlyExpense");

        Button submitFutureExpense = findViewById(R.id.submitFutureExpense);

        CalendarView calendar = findViewById(R.id.calendarView);

        EditText futureExpenseAmount = findViewById(R.id.futureExpenseAmount);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = (i1 + 1) + "/" + i2 + "/" + i;
            }
        });

        submitFutureExpense.setOnClickListener(unused -> submitFutureExpenseClicked(futureExpenseAmount));
    }

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