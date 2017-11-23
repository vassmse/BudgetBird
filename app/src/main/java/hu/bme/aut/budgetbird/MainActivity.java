package hu.bme.aut.budgetbird;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

   // private EditText nameEditText;
   // private EditText amountEditText;
   // private ToggleButton typeChooserButton;
   // private Button saveButton;
    private LinearLayout listOfRows;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // nameEditText = (EditText) findViewById(R.id.salary_name);
       // amountEditText = (EditText) findViewById(R.id.salary_amount);
      //  typeChooserButton = (ToggleButton)findViewById(R.id.expense_or_income);
     //   saveButton = (Button) findViewById(R.id.save_button);
        listOfRows = (LinearLayout) findViewById(R.id.list_of_rows);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || amountEditText.getText().toString().isEmpty()){
                    Snackbar.make(view,R.string.warn_message,Snackbar.LENGTH_LONG).show();
                    return;
                }

                View rowItem = inflater.inflate(R.layout.salary_row, null);
                ImageView icon = (ImageView) rowItem.findViewById(R.id.salary_direction_icon);
                TextView rowItemSalaryName = (TextView) rowItem.findViewById(R.id.row_salary_name);
                TextView rowItemSalaryAmount = (TextView) rowItem.findViewById(R.id.row_salary_amount);

                icon.setImageResource(typeChooserButton.isChecked() ? R.drawable.expense : R.drawable.income);
                rowItemSalaryName.setText(nameEditText.getText().toString());
                rowItemSalaryAmount.setText(amountEditText.getText().toString());

                listOfRows.addView(rowItem);

            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCost.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
