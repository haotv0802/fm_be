package fm.api.rest.moneyflow.validators;

import fm.api.rest.moneyflow.Item;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by haoho on 4/25/20.
 */
@Service("moneyFlowAddValidator")
public class MoneyFlowAddValidator implements Validator<Item> {

  private final IMoneyFlowDao expensesDao;

  public MoneyFlowAddValidator(@Qualifier("moneyFlowDao") IMoneyFlowDao expensesDao) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public String defaultFaultCode() {
    return "expense.id.invalid"; // TODO make it more sense
  }

  /**
   * Validate if the amount is null or not. Since amount value is the most important in every expense.
   * @param item
   * @param faultCode - custom fault code
   * @param args      - custom context
   */
  @Override
  public void validate(Item item, String faultCode, Object... args) {
    Assert.notNull(item);

    if (null == item.getAmount()) {
      throw new ValidationException("moneyflow.add.amount.notnull");
    }
  }
}
