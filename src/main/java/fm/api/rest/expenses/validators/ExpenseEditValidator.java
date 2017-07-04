package fm.api.rest.expenses.validators;

import fm.api.rest.expenses.interfaces.IExpensesDao;
import fm.common.ValidationException;
import fm.common.Validator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by haho on 7/4/2017.
 */
@Service("expenseEditValidator")
public class ExpenseEditValidator implements Validator<ExpenseEditValidation> {

  private final IExpensesDao expensesDao;

  @Autowired
  public ExpenseEditValidator(@Qualifier("expensesDao") IExpensesDao expensesDao) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public String defaultFaultCode() {
    return "expense.id.invalid";
  }

  @Override
  public void validate(ExpenseEditValidation expenseEditValidation, String faultCode, Object... args) {
    if (!expensesDao.
        checkIfLoginUserOwner(
            expenseEditValidation.getExpenseId(),
            expenseEditValidation.getUserId())
        ) {
      throw new ValidationException("expense.userId.invalid");
    }
  }
}