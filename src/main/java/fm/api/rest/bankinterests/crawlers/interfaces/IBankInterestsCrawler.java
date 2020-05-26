package fm.api.rest.bankinterests.crawlers.interfaces;

import fm.api.rest.bankinterests.crawlers.BankInterestsModel;

import java.util.List;
import java.util.Map;

/**
 * SAVING:
 * https://vietnambiz.vn/so-sanh-lai-suat-ngan-hang-thang-5-2020-lai-suat-ki-han-3-thang-cao-nhat-o-dau-20200507153731568.htm
 * https://webgia.com/lai-suat/
 * <p>
 * LOAN:
 * https://bankergroup.vn/?ref=adword1&gclid=EAIaIQobChMIufWS2KGJ4wIVzo2PCh0gyQaAEAAYASAAEgLTDvD_BwE#vay-the-chap
 * https://docs.google.com/spreadsheets/d/e/2PACX-1vSUt0E_489JwlkrN7gAOFQxVPnLRbayNAvW76KqCr5eiRC2yLGhorNKvmr3rdxwOWyjMSMmbHRb45Vz/pubhtml/sheet?headers=false&gid=1719343736
 */
public interface IBankInterestsCrawler {
    Map<String, List<BankInterestsModel>> crawl();
}
