package hu.bme.aut.budgetbird.model;

import com.orm.SugarRecord;

/**
 * Created by vassm on 2017. 11. 24..
 */

//TODO: costtypes for income, expense
public class CostType extends SugarRecord {
    private String type;
    private boolean isExpense;

    public CostType(String type, boolean isExpense)
    {
        this.type = type;
        this.isExpense = isExpense;
    }

    public String getType()
    {
        return type;
    }

    public boolean isExpense()
    {
        return isExpense;
    }
}

