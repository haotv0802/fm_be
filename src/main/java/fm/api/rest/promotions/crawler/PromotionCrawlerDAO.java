package fm.api.rest.promotions.crawler;
/**
 * Quy created on 3/11/2020
 */


import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("promotionCrawlerDao")
public class PromotionCrawlerDAO implements IPromotionCrawlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public PromotionCrawlerDAO(NamedParameterJdbcTemplate namedTemplate){
        Assert.notNull(namedTemplate);
        this.namedTemplate = namedTemplate;

    }

    // Save promotion Value into DB
    @Override
    public boolean savePromotion(Map<String, List> listMapPromotions) {
        return false;
    }
}
