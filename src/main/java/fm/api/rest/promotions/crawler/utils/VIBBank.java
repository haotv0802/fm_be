//package fm.api.rest.promotions.crawler.utils;
///* Quy created on 3/11/2020  */
//
//import fm.api.rest.bank.BankPresenter;
//import fm.api.rest.promotions.PromotionPresenter;
//import fm.api.rest.promotions.crawler.PromotionCrawlerDAO;
//import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
//import fm.api.rest.promotions.crawler.interfaces.IBankPromotionService;
//import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.io.IOException;
//import java.util.*;
//
//@Service("vibBank")
//public class VIBBank implements IBankPromotionService {
//    private static final Logger LOGGER = LogManager.getLogger(VIBBank.class);
//    private final String mainLink="https://www.vib.com.vn/";
//    private final PromotionUtils utils = new PromotionUtils();
//    private Set<String> listDetailPromoLink = new HashSet<>();
//    private IPromotionCrawlerDAO promotionCrawlerDAO;
//
//    @Autowired
//    public VIBBank(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO promotionCrawlerDAO){
//        Assert.notNull(promotionCrawlerDAO);
//        this.promotionCrawlerDAO = promotionCrawlerDAO;
//    }
//
//
//    @Override
//    public Map<String,List<PromotionCrawlerModel>> getListPromotionInfo(List<PromotionPresenter> promotionPresenterList) {
//        List<String> listLinks = utils.getBankPromotionLinks("./properties/VIB.properties","VIB");
//        if(!listLinks.isEmpty()){
//            try {
//                Map<String,List<PromotionCrawlerModel>> listPromotion= new TreeMap<>();
//                for(int i = 0 ; i<listLinks.size();i++){
//                    List<PromotionCrawlerModel> listModel = new ArrayList<>();
//                    Document pagePromoByCate = Jsoup.connect(listLinks.get(i).toString()).timeout(1000*5).get();
//                    Element promotionContainer = pagePromoByCate.getElementById("promotionList");
//                    Elements promotionbox = promotionContainer.select(".vib-v2-world-box");
//                    for (Element el : promotionbox){
//                        String linkPromoDetail = mainLink + el.select("a").attr("href");
//                        if(!listDetailPromoLink.add(linkPromoDetail)){
//                            LOGGER.error("THe LLink detail is Existed");
//                        }
//                    }
//                    for(String link : listDetailPromoLink){
//                        Thread.sleep(2000);
//                        PromotionCrawlerModel model = getPromotionFromLink(link,i);
//                        if(checkInfoExit(model,promotionPresenterList)){ // check if data is existed do update or else insert new info
//                            listModel.add(model);
//                        }
//
//
//                    }
//                    String cateName = listLinks.get(i).split("\\?cate=")[1].split("&name=")[1];
//                    listPromotion.put(cateName,listModel);
//
//                }
//                String[] headers = {"Bank","TiTle","Contain","Discount","Category","Start Beign","Date Expire", "Html","Link"};
//                utils.exportProvisionExcelFile(listPromotion,"VIBBank",headers);
//                listDetailPromoLink.clear();
//                return listPromotion;
//            }catch (IOException e){
//                e.printStackTrace();
//            }catch (InterruptedException ex){
//                ex.printStackTrace();
//            }
//
//        }
//        return null;
//    }
//
//    @Override
//    public PromotionCrawlerModel getPromotionFromLink(String link, int categoryId) {
//        try {
//            Document promotionPage = Jsoup.connect(link).get();
//            Elements promotionPageEls = promotionPage.getElementsByClass("vib-v2-wrapper_new");
//            String title = getDetail(promotionPageEls,".vib-v2-title-promotion-detail","Title");
//            String endDate = getDetail(promotionPageEls,".vib-v2-date-promotion","End Date").replaceAll("/","-");
//            String content = getDetail(promotionPageEls,".vib-v2-left-body-world-detail","Content");
//            List<String> location = getLocations(promotionPageEls);
//            String htmlText  = getDetail(promotionPageEls,".vib-v2-left-body-world-detail","HTML");
//            PromotionCrawlerModel model = new PromotionCrawlerModel(title,content,utils.getProvision(content)!=null?utils.getProvision(content):"0","",endDate,categoryId,2,htmlText,link,"IMG","CARD TYPE","CONDITION","LOCATION");
//            return model;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public String getDetail(Elements container, String selector, String tagName) {
//        try {
//            for(Element item : container){
//                String result= item.select(selector).text();
//                if(!result.equals("")){
//                    if (tagName.equals("End Date")){
//                        return utils.getDate(result);
//                    }else if(tagName.equals("HTML")){
//                        return item.select(selector).outerHtml();
//                    }
//                    else{
//                        return result;
//                    }
//
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<String> getLocations(Elements container) {
//        List<String> locationLists = new ArrayList<>();
//        for (Element item : container){
//            Elements locationContainer = item.select(".vib-v2-box-place-map");
//            for(Element locaationEl : locationContainer){
//                String location = locaationEl.text();
//                locationLists.add(location);
//            }
//        }
//        return locationLists;
//    }
//
//    private boolean checkInfoExit(PromotionCrawlerModel model,List<PromotionPresenter> list){
//            for(PromotionPresenter item : list){
//                if(model.getTitle().equals(item.getTitle())){
//                    if(model.getContent().equals(item.getContent())){
//                        if(!model.getDiscount().equals(item.getDiscount()) || !model.getEndDate().equals(item.getEndDate())){
//                            //do update
//                        }else {
//                            return true;
//                        }
//                    }
//                }
//            }
//        return false;
//    }
//    private List<PromotionPresenter> initBankData(int bankId ){
//        List<PromotionPresenter> listBankDataInfo = new ArrayList<>();
//        listBankDataInfo = promotionCrawlerDAO.getPrmoTionByBankIdAndCate(bankId);
//        return  listBankDataInfo;
//    }
//}
