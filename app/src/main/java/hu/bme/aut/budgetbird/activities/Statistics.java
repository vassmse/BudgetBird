package hu.bme.aut.budgetbird.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.budgetbird.R;
import hu.bme.aut.budgetbird.model.CostType;
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

    private void loadCharts()
    {
        List<PieEntry> expenses = new ArrayList<>();
        List<PieEntry> incomes = new ArrayList<>();

        if(businessLayer.GetCosts(CostType.Clothes)>0) expenses.add(new PieEntry(businessLayer.GetCosts(CostType.Clothes),getResources().getString(R.string.cloth)));
        if(businessLayer.GetCosts(CostType.Food)>0) expenses.add(new PieEntry(businessLayer.GetCosts(CostType.Food),getResources().getString(R.string.food)));
        if(businessLayer.GetCosts(CostType.Transport)>0) expenses.add(new PieEntry(businessLayer.GetCosts(CostType.Transport),getResources().getString(R.string.transport)));
        if(businessLayer.GetCosts(CostType.Other)>0) expenses.add(new PieEntry(businessLayer.GetCosts(CostType.Other),getResources().getString(R.string.other)));

        if(businessLayer.GetCosts(CostType.Salary)>0) incomes.add(new PieEntry(businessLayer.GetCosts(CostType.Salary),getResources().getString(R.string.salary)));
        if(businessLayer.GetCosts(CostType.Gambling)>0) incomes.add(new PieEntry(businessLayer.GetCosts(CostType.Gambling),getResources().getString(R.string.gambling)));
        if(businessLayer.GetCosts(CostType.Other)>0) incomes.add(new PieEntry(businessLayer.GetCosts(CostType.Other),getResources().getString(R.string.other)));

        PieDataSet dataSetExpenses = new PieDataSet(expenses,getResources().getString(R.string.expenses));
        dataSetExpenses.setColors(ColorTemplate.LIBERTY_COLORS);

        PieDataSet dataSetIncomes = new PieDataSet(incomes,getResources().getString(R.string.incomes));
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
