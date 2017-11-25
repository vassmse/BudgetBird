package hu.bme.aut.budgetbird.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.data.DataManager;

public class History extends AppCompatActivity {

    private DatePicker datePicker;
    private LinearLayout listOfRows;
    private LayoutInflater inflater;
    private Button showButton;

    private DataManager businessLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        businessLayer = DataManager.getInstance();

        datePicker = (DatePicker) findViewById(R.id.hisorty_datepicker);
        showButton = (Button) findViewById(R.id.show_history_button);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfRows = (LinearLayout) findViewById(R.id.list_of_history);
                inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                listOfRows.removeAllViews();

                //TODO: add just the new item
                for(int i = 0; i<businessLayer.GetCostsSum().size(); i++)
                {
                    View rowItem = inflater.inflate(R.layout.history_row, null);
                    ImageView icon = (ImageView) rowItem.findViewById(R.id.salary_direction_icon);
                    TextView rowItemSalaryName = (TextView) rowItem.findViewById(R.id.row_salary_name);
                    TextView rowItemSalaryAmount = (TextView) rowItem.findViewById(R.id.row_salary_amount);

                    icon.setImageResource(getImageResource(businessLayer.GetCostsSum().get(i).getCostType()));
                    rowItemSalaryName.setText(businessLayer.GetCostsSum().get(i).getName());
                    rowItemSalaryAmount.setText(String.valueOf(businessLayer.GetCostsSum().get(i).getAmount())+" Ft");

                    listOfRows.addView(rowItem);
                }
            }
        });

    }

    private @DrawableRes
    int getImageResource(String costType) {
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
