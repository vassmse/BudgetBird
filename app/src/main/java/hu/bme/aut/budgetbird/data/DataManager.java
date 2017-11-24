package hu.bme.aut.budgetbird.data;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.bme.aut.budgetbird.model.Cost;
import hu.bme.aut.budgetbird.model.CostType;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class DataManager {
    private static DataManager instance;

    private List<Cost> costs = new ArrayList<>();
    private List<CostType> types = new ArrayList<>();

    private DataManager() {
        setDummyTypes();
        setDummyCosts();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void AddCost(String name, int ammount, boolean isExpense, String costType)
    {
        Date currentTime =  Calendar.getInstance().getTime();
        costs.add(new Cost(name, ammount, currentTime, isExpense,costType ));
    }

    public List<Cost> GetCosts() {
        return costs;
    }

    //TODO: every costtype
    public int GetCosts(String type) {

        Double amount = 0.0;

        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i).getCostType() == type)
                amount += costs.get(i).getAmount();
        }

        return amount.intValue();
    }

    public List<String> GetIncomeTypes()
    {
        List<String> incomeTypes = new ArrayList<>();

        for(int i=0;i<types.size();i++)
        {
            if(!types.get(i).isExpense())
                incomeTypes.add(types.get(i).getType());
        }
        return  incomeTypes;
    }

    public List<String> GetExpenseTypes()
    {
        List<String> expenseTypes = new ArrayList<>();

        for(int i=0;i<types.size();i++)
        {
            if(types.get(i).isExpense())
                expenseTypes.add(types.get(i).getType());
        }
        return  expenseTypes;
    }

    private void setDummyTypes()
    {
        CostType type1 = new CostType("Étel", true);
        CostType type2 = new CostType("Utazás", true);
        CostType type3 = new CostType("Egyéb", true);
        CostType type4 = new CostType("Fizetés", false);
        CostType type5 = new CostType("Ösztöndíj", false);
        CostType type6 = new CostType("Egyéb", false);

        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        types.add(type5);
        types.add(type6);
    }

    private void setDummyCosts()
    {
        Date date = new Date(2017,11,24);
        Cost cost1 = new Cost("Pizza", 1200, date, true, "Étel");
        Cost cost2 = new Cost("Ruhh", 2200, date, true, "Utazás");
        Cost cost3 = new Cost("Másik pizza", 1500, date, true, "Étel");

        costs.add(cost1);
        costs.add(cost2);
        costs.add(cost3);

        Cost cost4 = new Cost("Fizu", 12000, date, false, "Fizetés");
        Cost cost5 = new Cost("Találtam", 2000, date, false,"Egyéb");
        Cost cost6 = new Cost("Fizu", 10500, date, false, "Fizetés");

        costs.add(cost4);
        costs.add(cost5);
        costs.add(cost6);
    }
}
