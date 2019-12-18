package fm.api.rest.moneyflow.validators;

import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.common.ValidationException;
import fm.common.Validator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by haho on 7/4/2017.
 */
@Service("moneyFlowEditValidator")
public class MoneyFlowEditValidator implements Validator<MoneyFlowEditValidation> {

  private final IMoneyFlowDao expensesDao;

  @Autowired
  public MoneyFlowEditValidator(@Qualifier("moneyFlowDao") IMoneyFlowDao expensesDao) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public String defaultFaultCode() {
    return "expense.id.invalid";
  }

  @Override
  public void validate(MoneyFlowEditValidation expenseEditValidation, String faultCode, Object... args) {
    if (!expensesDao.
        checkIfLoginUserOwner(
            expenseEditValidation.getExpenseId(),
            expenseEditValidation.getUserId())
        ) {
      throw new ValidationException("expense.userId.invalid");
    }
  }
}
