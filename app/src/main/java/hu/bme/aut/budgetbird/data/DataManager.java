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
        types = getTypes();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void AddCost(String name, int amount, boolean isExpense, String costType)
    {
        Date currentTime =  Calendar.getInstance().getTime();
        Cost cost = new Cost(name, amount, currentTime, isExpense,costType );
        cost.save();
        costs.add(cost);
    }

    public List<Cost> GetCostsSum() {
        return costs;
    }

    //TODO: every costtype
    public int GetCostsSum(String type) {

        int amount = 0;

        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i).getCostType().equals(type))
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
        return expenseTypes;
    }

    private ArrayList<CostType> getTypes()
    {
        ArrayList<CostType> costTypes = new ArrayList<>();

        costTypes.add(new CostType("Étel", true));
        costTypes.add(new CostType("Vásárlás", true));
        costTypes.add(new CostType("Lakhatás", true));
        costTypes.add(new CostType("Közlekedés", true));
        costTypes.add(new CostType("Szórakozás", true));
        costTypes.add(new CostType("Egyéb", true));

        costTypes.add(new CostType("Fizetés", false));
        costTypes.add(new CostType("Ösztöndíj", false));
        costTypes.add(new CostType("Ajándék", false));
        costTypes.add(new CostType("Szerencsejáték", false));
        costTypes.add(new CostType("Egyéb", false));

        return costTypes;
    }

}
