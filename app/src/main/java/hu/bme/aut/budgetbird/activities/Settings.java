package hu.bme.aut.budgetbird.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.data.DataManager;

public class Settings extends AppCompatActivity {

    private Switch switchValue;
    private EditText textValue;
    private Button saveButton;

    private DataManager businessLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        businessLayer = DataManager.getInstance();

        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getSharedPreferences("SharedPreference", Context.MODE_PRIVATE);
        boolean isActive = sharedPref.getBoolean(getString(R.string.saved_isNotification_active), true);
        String notificationValue = sharedPref.getString(getString(R.string.saved_isNotification_value), "0");

        switchValue = (Switch) findViewById(R.id.notification_switch);
        textValue = (EditText) findViewById(R.id.notification_value);
        saveButton = (Button) findViewById(R.id.save_settings_button);

        textValue.setText(notificationValue);
        switchValue.setChecked(isActive);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("SharedPreference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.saved_isNotification_active), switchValue.isChecked());
                editor.putString(getString(R.string.saved_isNotification_value), textValue.getText().toString());
                editor.commit();

                businessLayer.setCostLimit(Integer.parseInt(textValue.getText().toString()));
                businessLayer.setCostLimitActive(switchValue.isChecked());

                finish();
            }
        });
    }
}
