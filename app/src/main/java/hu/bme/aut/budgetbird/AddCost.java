package hu.bme.aut.budgetbird;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import hu.bme.aut.budgetbird.data.CostType;
import hu.bme.aut.budgetbird.data.DataManager;

public class AddCost extends AppCompatActivity {

     private EditText nameEditText;
     private EditText amountEditText;
     private ToggleButton typeChooserButton;
     private Button saveButton;

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


        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty()){
                    Snackbar.make(view,R.string.warn_message,Snackbar.LENGTH_LONG).show();
                    return;
                }

                businessLayer.AddCost(nameEditText.getText().toString(), Double.parseDouble(amountEditText.getText().toString()), typeChooserButton.isChecked(), CostType.Other);
                finish();
            }
        });
    }
}
