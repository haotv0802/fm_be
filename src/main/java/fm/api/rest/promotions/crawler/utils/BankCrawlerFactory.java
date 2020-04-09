// /* Quy created on 4/8/2020 */
 package fm.api.rest.promotions.crawler.utils;

 import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
 import io.jsonwebtoken.lang.Assert;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;

 @Service("bankCrawlerFactory")
 public class BankCrawlerFactory {

     private IBankPromotionCrawler vibCrawler;
     private IBankPromotionCrawler scbCrawler;
     private IBankPromotionCrawler shinhanCrawler;

     public BankCrawlerFactory(@Qualifier("vibCrawler") IBankPromotionCrawler vibCrawler,
                               @Qualifier("scbCrawler") IBankPromotionCrawler scbCrawler,
                               @Qualifier("shinhanCrawler") IBankPromotionCrawler shinhanCrawler) {


         Assert.notNull(vibCrawler);

         this.vibCrawler = vibCrawler;
         this.scbCrawler = scbCrawler;
         this.shinhanCrawler = shinhanCrawler;

     }

     public IBankPromotionCrawler getBankCrawler(String bankName) {

         switch (bankName) {
             case "vib":
                 return this.vibCrawler;
             case "scb":
                 return this.scbCrawler;
             case "shinhan":
                 return this.shinhanCrawler;
             default:
                 return null;
         }

     }
 }
