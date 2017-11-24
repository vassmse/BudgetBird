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
        Cost cost2 = new Cost("Ruhh", 2200, date, true, CostType.Clothes);
        Cost cost3 = new Cost("Másik pizza", 1500, date, true, CostType.Food);

        costs.add(cost1);
        costs.add(cost2);
        costs.add(cost3);

        Cost cost4 = new Cost("Fizu", 12000, date, false, CostType.Salary);
        Cost cost5 = new Cost("Találtam", 2000, date, false, CostType.Other);
        Cost cost6 = new Cost("Fizu", 10500, date, false, CostType.Salary);

        costs.add(cost4);
        costs.add(cost5);
        costs.add(cost6);
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
        //TODO: null, if press back
        return newCost;
    }

    public List<Cost> GetCosts() {
        return costs;
    }

    //TODO: every costtype
    public int GetCosts(CostType type) {

        Double amount = 0.0;

        for (int i = 0; i < costs.size(); i++) {
            if (costs.get(i).getCostType() == type)
                amount += costs.get(i).getAmmount();
        }

        return amount.intValue();
    }
}
