package hu.bme.aut.budgetbird.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import hu.bme.aut.budgetbird.R;

public class History extends AppCompatActivity {

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        datePicker = (DatePicker) findViewById(R.id.hisorty_datepicker);

    }
}
