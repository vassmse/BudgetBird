package hu.bme.aut.budgetbird.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.data.DataManager;

public class AddCost extends AppCompatActivity {

    private EditText nameEditText;
    private EditText amountEditText;
    private Button saveButton;
    private Spinner typeSpinner;
    private DataManager businessLayer;
    private RadioGroup radioGroup;
    private RadioButton radioExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        businessLayer = DataManager.getInstance();

        nameEditText = (EditText) findViewById(R.id.salary_name);
        amountEditText = (EditText) findViewById(R.id.salary_amount);
        saveButton = (Button) findViewById(R.id.save_button);
        radioGroup = (RadioGroup) findViewById(R.id.radioCostType);
        radioExpense = (RadioButton) findViewById(R.id.radioExpense);

        typeSpinner = (Spinner) findViewById(R.id.type_spinner);

        if (radioExpense.isChecked()) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, businessLayer.GetExpenseTypes());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(dataAdapter);
        } else {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, businessLayer.GetIncomeTypes());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(dataAdapter);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty()) {
                    Snackbar.make(view, R.string.warn_message, Snackbar.LENGTH_LONG).show();
                    return;
                }

                businessLayer.AddCost(nameEditText.getText().toString(), Integer.parseInt(amountEditText.getText().toString()), radioExpense.isChecked(), typeSpinner.getSelectedItem().toString());

                if (businessLayer.IsCostLimitEnded() > 0)
                    sendNotification(businessLayer.IsCostLimitEnded());
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioExpense.isChecked()) {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddCost.this, android.R.layout.simple_spinner_item, businessLayer.GetExpenseTypes());
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    typeSpinner.setAdapter(dataAdapter);
                } else {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddCost.this, android.R.layout.simple_spinner_item, businessLayer.GetIncomeTypes());
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    typeSpinner.setAdapter(dataAdapter);
                }
            }

        });
    }

    private void sendNotification(int amount) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.peacock)
                        .setVibrate(new long[]{0, 500, 100, 500})
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(getString(R.string.notification_text, amount));

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        mNotificationManager.notify(001, mBuilder.build());
    }

}
