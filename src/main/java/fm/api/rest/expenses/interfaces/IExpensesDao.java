package fm.api.rest.expenses.interfaces;

import fm.api.rest.expenses.ExpenseBean;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IExpensesDao {
  List<ExpenseBean> getExpenses();
}