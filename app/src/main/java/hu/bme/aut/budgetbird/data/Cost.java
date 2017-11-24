package hu.bme.aut.budgetbird.data;

import java.util.Date;

/**
 * Created by vassm on 2017. 11. 24..
 */

public class Cost {
    private String name;
    private double ammount;
    private Date date;
    private boolean isExpense;
    private CostType costType;

    public Cost(String name, double ammount, Date date, boolean isExpense, CostType costType)
    {
        this.name = name;
        this.ammount = ammount;
        this.date = date;
        this.isExpense = isExpense;
        this.costType = costType;
    }

    public String getName(){
        return name;
    }

    public double getAmmount()
    {
        return ammount;
    }

    public Date getDate()
    {
        return date;
    }

    public boolean isExpense()
    {
        return isExpense;
    }

    public CostType getCostType()
    {
        return  costType;
    }
}
