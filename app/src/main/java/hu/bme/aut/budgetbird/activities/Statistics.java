package hu.bme.aut.budgetbird.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.data.DataManager;

public class Statistics extends AppCompatActivity {

    private PieChart chartExpenses;
    private PieChart chartIncomes;

    private DataManager businessLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        businessLayer = DataManager.getInstance();

        chartExpenses = (PieChart) findViewById(R.id.chartExpenses);
        chartIncomes = (PieChart) findViewById(R.id.chartIncomes);

        loadCharts();
    }

    private void loadCharts() {
        List<PieEntry> expenses = new ArrayList<>();
        List<PieEntry> incomes = new ArrayList<>();

        if (businessLayer.GetCostsSum("Étel") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Étel"), getResources().getString(R.string.cloth)));
        if (businessLayer.GetCostsSum("Utazás") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Utazás"), getResources().getString(R.string.food)));
        if (businessLayer.GetCostsSum("Vásárlás") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Vásárlás"), getResources().getString(R.string.shopping)));
        if (businessLayer.GetCostsSum("Lakhatás") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Lakhatás"), getResources().getString(R.string.home)));
        if (businessLayer.GetCostsSum("Közlekedés") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Közlekedés"), getResources().getString(R.string.transport)));
        if (businessLayer.GetCostsSum("Szórakozás") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Szórakozás"), getResources().getString(R.string.fun)));
        if (businessLayer.GetCostsSum("Egyéb") > 0)
            expenses.add(new PieEntry(businessLayer.GetCostsSum("Egyéb"), getResources().getString(R.string.other)));

        if (businessLayer.GetCostsSum("Fizetés") > 0)
            incomes.add(new PieEntry(businessLayer.GetCostsSum("Fizetés"), getResources().getString(R.string.salary)));
        if (businessLayer.GetCostsSum("Ösztöndíj") > 0)
            incomes.add(new PieEntry(businessLayer.GetCostsSum("Ösztöndíj"), getResources().getString(R.string.scholarship)));
        if (businessLayer.GetCostsSum("Ajándék") > 0)
            incomes.add(new PieEntry(businessLayer.GetCostsSum("Ajándék"), getResources().getString(R.string.gift)));
        if (businessLayer.GetCostsSum("Szerencsejáték") > 0)
            incomes.add(new PieEntry(businessLayer.GetCostsSum("Szerencsejáték"), getResources().getString(R.string.gambling)));
        if (businessLayer.GetCostsSum("Egyéb") > 0)
            incomes.add(new PieEntry(businessLayer.GetCostsSum("Egyéb"), getResources().getString(R.string.other)));

        PieDataSet dataSetExpenses = new PieDataSet(expenses, getResources().getString(R.string.expenses));
        dataSetExpenses.setColors(ColorTemplate.LIBERTY_COLORS);

        PieDataSet dataSetIncomes = new PieDataSet(incomes, getResources().getString(R.string.incomes));
        dataSetIncomes.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData dataExpenses = new PieData(dataSetExpenses);
        chartExpenses.setData(dataExpenses);
        chartExpenses.getDescription().setEnabled(false);
        chartExpenses.invalidate();

        PieData dataIncomes = new PieData(dataSetIncomes);
        chartIncomes.setData(dataIncomes);
        chartIncomes.getDescription().setEnabled(false);
        chartIncomes.invalidate();
    }
}
