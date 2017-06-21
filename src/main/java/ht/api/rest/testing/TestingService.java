package ht.api.rest.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 3/13/2017.
 */
@Service
public class TestingService {
  private static final Logger logger = LogManager.getLogger(TestingService.class);

  @Autowired
  private TestingDao testingDao;

  public List<AuthorityBean> getAuthorities() {
    return testingDao.getAuthorities();
  }
}
