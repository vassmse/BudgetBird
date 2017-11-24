package hu.bme.aut.budgetbird.data;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class DataManager {
    private static DataManager instance;

    private List<Cost> costs = new ArrayList<>();
    private Cost newCost;

    private DataManager() {
        Date date = new Date(2017,11,24);
        Cost cost1 = new Cost("Pizza", 1200, date, true, CostType.Food);
        costs.add(cost1);
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void AddCost(String name, double ammount, boolean isExpense, CostType costType)
    {
        Date currentTime =  Calendar.getInstance().getTime();
        newCost = new Cost(name, ammount, currentTime, isExpense,costType );
        costs.add(newCost);
    }

    public Cost GetAddedCost()
    {
        return newCost;
    }

    public List<Cost> GetCosts() {
        return costs;
    }
}
