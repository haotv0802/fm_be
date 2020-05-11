package fm.api.rest.moneyflow.validators;

import fm.api.rest.moneyflow.ItemPresenter;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by haho on 7/4/2017.
 */
@Service("moneyFlowDeleteValidator")
public class MoneyFlowDeleteValidator implements Validator<MoneyFlowDeleteValidation> {

  private final IMoneyFlowDao expensesDao;

  @Autowired
  public MoneyFlowDeleteValidator(@Qualifier("moneyFlowDao") IMoneyFlowDao expensesDao) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public String defaultFaultCode() {
    return "expense.id.invalid";
  }

  /**
   * Validate if specific expense belongs to a right user.
   * @param expenseEditValidation
   * @param faultCode - custom fault code
   * @param args      - custom context
   */
  @Override
  public void validate(MoneyFlowDeleteValidation expenseEditValidation, String faultCode, Object... args) {

    Assert.notNull(expenseEditValidation);
    Assert.notNull(expenseEditValidation.getUserId());
    Assert.notNull(expenseEditValidation.getExpenseId());

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    javax.validation.Validator validator = factory.getValidator();

    if (!expensesDao.
        checkIfLoginUserOwner(
            expenseEditValidation.getExpenseId(),
            expenseEditValidation.getUserId())
        ) {
      throw new ValidationException("moneyflow.userId.invalid");
    }
  }
}
