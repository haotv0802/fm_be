package fm.api.rest.payment.methods;

import fm.api.rest.expenses.ExpensesDao;
import fm.api.rest.payment.methods.beans.CardInformation;
import fm.api.rest.payment.methods.interfaces.IPaymentMethodsDao;
import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.smartcardio.Card;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
@Repository("paymentMethodsDao")
public class PaymentMethodsDao implements IPaymentMethodsDao {

  private static final Logger LOGGER = LogManager.getLogger(ExpensesDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public PaymentMethodsDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);

    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<CardInformation> getCardsInformation(int userId) {
    final String sql = "SELECT                                                    "
                     + "	c.id, c.card_number, p.name card_type, c.name card_info "
                     + "FROM                                                      "
                     + "	fm_cards_information c                                  "
                     + "		INNER JOIN                                            "
                     + "	fm_payment_methods p ON c.card_type_id = p.id           "
                     + "WHERE                                                     "
                     + "	c.user_id = :userId                                     "
                     + "		AND c.is_terminated = FALSE                           "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, new RowMapper<CardInformation>() {
      @Override
      public CardInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        CardInformation card = new CardInformation();
        card.setId(rs.getInt("id"));
        card.setCardInfo(rs.getString("card_info"));
        card.setCardNumber(rs.getString("card_number"));
        card.setCardType(rs.getString("card_type"));
        return card;
      }
    });
  }
}
