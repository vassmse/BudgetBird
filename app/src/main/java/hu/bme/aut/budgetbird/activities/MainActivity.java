package hu.bme.aut.budgetbird.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.data.DataManager;

public class MainActivity extends AppCompatActivity {

   // private EditText nameEditText;
   // private EditText amountEditText;
   // private ToggleButton typeChooserButton;
   // private Button saveButton;
    private LinearLayout listOfRows;
    private LayoutInflater inflater;

    private DataManager businessLayer;

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

        businessLayer = DataManager.getInstance();

        SharedPreferences sharedPref = getSharedPreferences("SharedPreference", Context.MODE_PRIVATE);
        boolean isActive = sharedPref.getBoolean(getString(R.string.saved_isNotification_active), true);
        String notificationValue = sharedPref.getString(getString(R.string.saved_isNotification_value), "0");
        businessLayer.setCostLimit(Integer.parseInt(notificationValue));
        businessLayer.setCostLimitActive(isActive);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addCostIntent = new Intent(MainActivity.this, AddCost.class);
                startActivity(addCostIntent);
            }
        });


    }

    protected void onStart() {
        super.onStart();

        listOfRows = (LinearLayout) findViewById(R.id.list_of_rows);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listOfRows.removeAllViews();

        //TODO: add just the new item
        for(int i = 0; i<businessLayer.GetCostsSum().size(); i++)
        {
            View rowItem = inflater.inflate(R.layout.salary_row, null);
            ImageView icon = (ImageView) rowItem.findViewById(R.id.salary_direction_icon);
            TextView rowItemSalaryName = (TextView) rowItem.findViewById(R.id.row_salary_name);
            TextView rowItemSalaryAmount = (TextView) rowItem.findViewById(R.id.row_salary_amount);

            icon.setImageResource(getImageResource(businessLayer.GetCostsSum().get(i).getCostType()));
            rowItemSalaryName.setText(businessLayer.GetCostsSum().get(i).getName());
            rowItemSalaryAmount.setText(String.valueOf(businessLayer.GetCostsSum().get(i).getAmount())+" Ft");

            listOfRows.addView(rowItem);
        }


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
            Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
            startActivity(settingsIntent);
            return true;
        }
        else if (id == R.id.action_history) {
            Intent historyIntent = new Intent(MainActivity.this, History.class);
            startActivity(historyIntent);
            return true;
        }
        else if (id == R.id.action_statistics) {
            Intent statisticsIntent = new Intent(MainActivity.this, Statistics.class);
            startActivity(statisticsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private @DrawableRes int getImageResource(String costType) {
        @DrawableRes int ret;
        switch (costType) {
            case "Étel":
                ret = R.drawable.type_food;
                break;
            case "Közlekedés":
                ret = R.drawable.type_transport;
                break;
            case "Vásárlás":
                ret = R.drawable.type_shopping;
                break;
            case "Lakhatás":
                ret = R.drawable.type_home;
                break;
            case "Szórakozás":
                ret = R.drawable.type_entertainment;
                break;
            case "Fizetés":
                ret = R.drawable.type_salary;
                break;
            case "Ösztöndíj":
                ret = R.drawable.type_university;
                break;
                case "Ajándék":
                ret = R.drawable.type_gift;
                break;
            case "Szerencsejáték":
                ret = R.drawable.type_gambling;
                break;
            default:
                ret = R.drawable.type_other_red;
        }
        return ret;
    }
}
