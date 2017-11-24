package hu.bme.aut.budgetbird.data;

import com.orm.SugarApp;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hu.bme.aut.budgetbird.model.Cost;
import hu.bme.aut.budgetbird.model.CostType;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class DataManager extends SugarApp {
    private static DataManager instance;

    private final List<Cost> costs;
    private List<CostType> types;

    private DataManager() {
        costs = Cost.listAll(Cost.class);
        types = new ArrayList<>();
        setDummyTypes();
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
        Cost cost = new Cost(name, ammount, currentTime, isExpense,costType );
        cost.save();
        costs.add(cost);
    }

    public List<Cost> GetCosts() {
        return costs;
    }

    //TODO: every costtype
    public int GetCosts(String type) {

        int amount = 0;

        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i).getCostType() == type)
                amount += costs.get(i).getAmount();
        }

        return amount;
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

}
