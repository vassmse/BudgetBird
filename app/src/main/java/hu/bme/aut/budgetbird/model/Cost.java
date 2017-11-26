package hu.bme.aut.budgetbird.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class Cost extends SugarRecord {
    private String name;
    private int amount;
    private Date date;
    private boolean isExpense;
    private String costType;

    public Cost(String name, int amount, Date date, boolean isExpense, String costType) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.isExpense = isExpense;
        this.costType = costType;
    }

    public Cost() {
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public String getCostType() {
        return costType;
    }


}
