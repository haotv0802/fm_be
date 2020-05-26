package fm.api.rest.bankinterests.crawlers.saving;

import fm.api.rest.bankinterests.crawlers.BankInterestsModel;
import fm.api.rest.bankinterests.crawlers.interfaces.IBankInterestsCrawler;

import java.util.List;
import java.util.Map;

/**
 * Created by HaoHo on 5/26/2020
 * https://vietnambiz.vn/so-sanh-lai-suat-ngan-hang-thang-5-2020-lai-suat-ki-han-3-thang-cao-nhat-o-dau-20200507153731568.htm
 */
public class VietnambizCrawler implements IBankInterestsCrawler {
    @Override
    public Map<String, List<BankInterestsModel>> crawl() {
        return null;
    }
}
