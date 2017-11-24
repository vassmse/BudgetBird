package hu.bme.aut.budgetbird.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class DataManager {
    private static DataManager instance;

    private List<Cost> costs = new ArrayList<Cost>();

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

    public List<Cost> getCosts() {
        return costs;
    }
}
