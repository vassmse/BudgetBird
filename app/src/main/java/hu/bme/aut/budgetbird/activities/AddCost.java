package hu.bme.aut.budgetbird.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.model.CostType;
import hu.bme.aut.budgetbird.data.DataManager;

public class AddCost extends AppCompatActivity {

     private EditText nameEditText;
     private EditText amountEditText;
     private ToggleButton typeChooserButton;
     private Button saveButton;
     private Spinner typeSpinner;

     private DataManager businessLayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cost);

        businessLayer = DataManager.getInstance();

        nameEditText = (EditText) findViewById(R.id.salary_name);
        amountEditText = (EditText) findViewById(R.id.salary_amount);
        typeChooserButton = (ToggleButton)findViewById(R.id.expense_or_income);
        saveButton = (Button) findViewById(R.id.save_button);

        typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        if(typeChooserButton.isChecked())
        {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, businessLayer.GetExpenseTypes());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(dataAdapter);
        }
        else{
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, businessLayer.GetIncomeTypes());
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeSpinner.setAdapter(dataAdapter);
        }

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty()){
                    Snackbar.make(view,R.string.warn_message,Snackbar.LENGTH_LONG).show();
                    return;
                }

                businessLayer.AddCost(nameEditText.getText().toString(), Integer.parseInt(amountEditText.getText().toString()), typeChooserButton.isChecked(), typeSpinner.getSelectedItem().toString());
                finish();
            }
        });

        typeChooserButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(typeChooserButton.isChecked())
                {
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddCost.this, android.R.layout.simple_spinner_item, businessLayer.GetExpenseTypes());
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    typeSpinner.setAdapter(dataAdapter);
                }
                else{
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddCost.this, android.R.layout.simple_spinner_item, businessLayer.GetIncomeTypes());
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    typeSpinner.setAdapter(dataAdapter);
                }
            }

        });
    }

}
