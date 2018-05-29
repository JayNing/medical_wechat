package com.zx.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zx.contants.Contants;
import com.zx.wx.dto.report.common.ReportResultDTO;
import com.zx.wx.http.entity.LevelMenu;
import com.zx.wx.http.entity.Menu;
import com.zx.wx.http.entity.MenuButton;
import com.zx.wx.http.entity.SubMenuButton;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:31:25
 * //TODO json工具类
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    private static Gson gson = new Gson();

    private static ObjectMapper mapper = new ObjectMapper();

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        mapper.setDateFormat(dateFormat);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static Gson getInstance() {
        return gson;
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JsonUtil fromJson Type:" + clazz.getName() + ", Json:" + json, e);
            return null;
        }
    }

    public static <T> List<T> fromListJson(String json, Class<T> clazz) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(json).getAsJsonArray();

            List<T> lst = new ArrayList<T>();
            for (final JsonElement js : array) {
                T entity = gson.fromJson(js, clazz);
                lst.add(entity);
            }

            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("JsonUtil fromJson Type:List<" + clazz.getName() + ">, Json:" + json, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T toObject(String content, T t) {
        T result = null;

        try {
            result = (T) mapper.readValue(content, t.getClass());
        } catch (JsonParseException e) {
            logger.info("JsonUtil   toObject  JsonParseException---------------> " + e);
            e.printStackTrace();
        } catch (JsonMappingException e) {
            logger.info("JsonUtil   toObject  JsonMappingException---------------> " + e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("JsonUtil   toObject  IOException---------------> " + e);
            e.printStackTrace();
        }

        return result;
    }

    public static String toJsonFromObj(Object obj) {
        JSONObject json = JSONObject.fromObject(obj);
        String strJson = json.toString();
        return strJson;
    }

    public static <T> String toJsonFromArry(List<T> list) {
        String pojoJson = JSONArray.fromObject(list).toString();
        return pojoJson;
    }

    public static void main(String[] args) {

        System.out.println(getDemoDataDetail());
    	
    }

    public static ReportResultDTO getDemoDataList(){

        String str = "{\"resultcode\":\"0\",\"reason\":\"success\",\"result\":[{\"tjbh\":\"818030012\",\"companyname\":\"\",\"examcategory\":\"常规体检\",\"checkupdate\":\"20180323\",\"tjzt\":\"2\",\"status\":\"2\",\"summarydoctor\":\"supervisor\",\"summarydate\":\"2018032717:02:51\",\"package\":\"A体检套餐（男士）\",\"packageid\":\"298\",\"items\":[{\"id\":\"10000001\",\"item\":\"一般检查\"},{\"id\":\"1000404\",\"item\":\"内科\"},{\"id\":\"10000005\",\"item\":\"眼科\"},{\"id\":\"10000009\",\"item\":\"耳鼻喉\"},{\"id\":\"10000010\",\"item\":\"口腔科检查\"},{\"id\":\"10000017\",\"item\":\"动态心电图\"},{\"id\":\"10000020\",\"item\":\"血细胞分析五分类\"},{\"id\":\"10000024\",\"item\":\"尿常规\"},{\"id\":\"10000027\",\"item\":\"空腹血糖\"},{\"id\":\"10000043\",\"item\":\"血脂2项\"},{\"id\":\"10000062\",\"item\":\"肝功能3项\"},{\"id\":\"10000074\",\"item\":\"肾功3项\"},{\"id\":\"10000213\",\"item\":\"胸部正位数字化摄影(DR)\"},{\"id\":\"10000287\",\"item\":\"腹部彩超\"},{\"id\":\"10000288\",\"item\":\"前列腺彩超\"},{\"id\":\"1000405\",\"item\":\"外科检查\"}]},{\"tjbh\":\"818030019\",\"companyname\":\"\",\"examcategory\":\"常规体检\",\"checkupdate\":\"20180428\",\"tjzt\":\"0\",\"status\":\"0\",\"summarydoctor\":\"\",\"summarydate\":\"\",\"package\":\"A体检套餐（男士）\",\"packageid\":\"298\",\"items\":[{\"id\":\"10000001\",\"item\":\"一般检查\"},{\"id\":\"1000404\",\"item\":\"内科\"},{\"id\":\"10000005\",\"item\":\"眼科\"},{\"id\":\"10000009\",\"item\":\"耳鼻喉\"},{\"id\":\"10000010\",\"item\":\"口腔科检查\"},{\"id\":\"10000017\",\"item\":\"动态心电图\"},{\"id\":\"10000020\",\"item\":\"血细胞分析五分类\"},{\"id\":\"10000024\",\"item\":\"尿常规\"},{\"id\":\"10000027\",\"item\":\"空腹血糖\"},{\"id\":\"10000043\",\"item\":\"血脂2项\"},{\"id\":\"10000062\",\"item\":\"肝功能3项\"},{\"id\":\"10000074\",\"item\":\"肾功3项\"},{\"id\":\"10000213\",\"item\":\"胸部正位数字化摄影(DR)\"},{\"id\":\"10000287\",\"item\":\"腹部彩超\"},{\"id\":\"10000288\",\"item\":\"前列腺彩超\"},{\"id\":\"1000405\",\"item\":\"外科检查\"}]}]}\n";

        str = str.replace("package","baoming");
        ReportResultDTO dto = fromJson(str, ReportResultDTO.class);
        return dto;
    }

    private static Menu getMenu() {
		Menu menu = new Menu();
		
		List<MenuButton> buttonList = new ArrayList<>();
		// 建3个导航菜单
		 LevelMenu tLevelMenuOne = new LevelMenu();
         tLevelMenuOne.setName(Contants.LEVEL_MENU_MEDICAL_APPOINTMENT_NAME);
         LevelMenu tLevelMenuTwo = new LevelMenu();
         tLevelMenuTwo.setName(Contants.LEVEL_MENU_HEALTH_REPORT_NAME);
         LevelMenu tLevelMenuThree = new LevelMenu();
         tLevelMenuThree.setName(Contants.LEVEL_MENU_PERSONAL_CENTER_NAME);
         
         // 第一个导航菜单的子菜单
         List<SubMenuButton> subOneMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_oneOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_PERSONAL_APPOINTMENT_NAME,Contants.SUB_MENU_PERSONAL_APPOINTMENT);
         
         SubMenuButton tSubMenuButton_oneTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_TEAM_APPOINTMENT_NAME,Contants.SUB_MENU_TEAM_APPOINTMENT);
         
         SubMenuButton tSubMenuButton_oneThree = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_PACKAGE_DETAILS_NAME,Contants.SUB_MENU_PACKAGE_DETAILS);
         
         subOneMenuButtonList.add(tSubMenuButton_oneOne);
         subOneMenuButtonList.add(tSubMenuButton_oneTwo);
         subOneMenuButtonList.add(tSubMenuButton_oneThree);
         
         // 加入第一导航菜单
         tLevelMenuOne.setSub_button(subOneMenuButtonList);
         
         // 第二个导航菜单的子菜单
         List<SubMenuButton> subSecondMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_secondOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_HEALTH_MANAGER_REPORT_NAME,Contants.SUB_MENU_HEALTH_MANAGER_REPORT);
         SubMenuButton tSubMenuButton_secondTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_MEDICAL_EXAMINATION_REPORT_NAME,Contants.SUB_MENU_MEDICAL_EXAMINATION_REPORT);
         
         subSecondMenuButtonList.add(tSubMenuButton_secondOne);
         subSecondMenuButtonList.add(tSubMenuButton_secondTwo);
         // 加入第二导航菜单
         tLevelMenuTwo.setSub_button(subSecondMenuButtonList);
         
      // 第三个导航菜单的子菜单
         List<SubMenuButton> subThreeMenuButtonList = new ArrayList<>();
         SubMenuButton tSubMenuButton_threeOne = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_APPOINTMENT_MANAGEMENT_NAME,Contants.SUB_MENU_APPOINTMENT_MANAGEMENT);
         SubMenuButton tSubMenuButton_threeTwo = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_ORDER_SERVER_NAME,Contants.SUB_MENU_ORDER_SERVER);
         
         SubMenuButton tSubMenuButton_threeThree = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_GUIDE_CHECK_SERVER_NAME,Contants.SUB_MENU_GUIDE_CHECK_SERVER);
         SubMenuButton tSubMenuButton_threeFour = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_HISTORY_ASQ_NAME,Contants.SUB_MENU_HISTORY_ASQ);
         
         SubMenuButton tSubMenuButton_threeFive = getSubMenuButton(Contants.WECHAT_MENU_TYPE_CLICK,
        		 Contants.SUB_MENU_MY_COLLECTION_NAME,Contants.SUB_MENU_MY_COLLECTION);
         
         
         subThreeMenuButtonList.add(tSubMenuButton_threeOne);
         subThreeMenuButtonList.add(tSubMenuButton_threeTwo);
         subThreeMenuButtonList.add(tSubMenuButton_threeThree);
         subThreeMenuButtonList.add(tSubMenuButton_threeFour);
         subThreeMenuButtonList.add(tSubMenuButton_threeFive);
         
         // 加入第二导航菜单
         tLevelMenuThree.setSub_button(subThreeMenuButtonList);
         
         
         buttonList.add(tLevelMenuOne);
         buttonList.add(tLevelMenuTwo);
         buttonList.add(tLevelMenuThree);
         
         menu.setButton(buttonList);
         
	    return menu;
	}
    
    private static SubMenuButton getSubMenuButton(String type, String name,
			String key) {
		  SubMenuButton tSubMenuButton_oneOne = new SubMenuButton();
	         tSubMenuButton_oneOne.setType(type);
	         tSubMenuButton_oneOne.setName(name);
		return tSubMenuButton_oneOne;
	}

    public static <T> T fromJsonForGson(String body, Type type) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(body, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return t;
    }

    public static ReportResultDTO getDemoDataDetail() {
        String str = "{\"resultcode\":\"0\",\"reason\":\"success\",\"result\":{\"xh\":\"f804c8da-721c-48ee-a61a-421cd31759ab\",\"tjbh\":\"818030012\",\"orgid\":\"\",\"checkupdate\":\"20180328\",\"name\":\"许同\",\"idcard\":null,\"sex\":\"1\",\"companyname\":\"\",\"examcategory\":\"常规体检\",\"packagename\":\"A体检套餐（男士）\",\"summary\":\"一、一般检查：\\r\\n舒张压严重偏高\\r\\n\\r\\n二、内科：\\r\\n1、扁平胸\\r\\n2、桶状胸\\r\\n3、佝偻病胸\\r\\n4、胸廓变形\\r\\n5、胸部局部隆起\\r\\n6、脊柱畸形引起的胸廓改变\\r\\n7、脊柱畸形所致胸廓改变\\r\\n8、心房纤颤\\r\\n9、A2>P2\\r\\n10、心包摩擦音\\r\\n11、二尖瓣区收缩期Ⅲ级吹风样杂音\\r\\n12、心音A2>P2,第二音亢进\\r\\n13、肺部听诊干啰音\\r\\n14、呼吸音减弱或消失\\r\\n15、肺部实音/伴语音变化\\r\\n16、肺部听诊湿啰音\\r\\n17、肺部捻发音\\r\\n18、哮鸣音\\r\\n19、肺部听诊提示过清音\\r\\n20、肝硬化\\r\\n21、肝肿大\\r\\n22、脾肿大\\r\\n23、上腹部压之不适\\r\\n24、阑尾部压痛\\r\\n25、腹部压痛\\r\\n26、双肾叩痛\\r\\n\\r\\n\",\"advice\":\"一、舒张压严重偏高：\\r\\n1、调整生活方式，限制烟酒，体育煅炼;\\r\\n2、药物剂量维持一段时间以后可以减量观察，如血压仍然能维持在正常水平，能以最低剂量维持血压正常则为理想，但不要轻易停药。\\r\\n\\r\\n\\r\\n二、扁平胸：\\r\\n扁平胸是由于先天性胸廓发育异常所致，多见于瘦长体型者，不需治疗\\r\\n\\r\\n\\r\\n三、上腹部压之不适：\\r\\n建议内科复查,诊治。\\r\\n\\r\\n\\r\\n四、肝肿大：\\r\\n1、饮食方面应提供足够的营养，食物要多样化，供给含氨基酸的高价蛋白质、多种维生素、低脂肪、少渣饮食，要防止粗糙多纤维食物损伤食道静脉，引起大出血。 \\r\\n2、血氨偏高或肝功能极差者，应限制蛋白质摄入，以免发生肝昏迷。出现腹水者应进低盐或无盐饮食。 　\\r\\n3、每日测量腹围和测定尿量，腹部肥胖可是自我鉴别脂肪肝的一大方法。 \\r\\n4、注意出血、紫癜、发热、精神神经症状的改变，并及时和医生取得联系。 　\\r\\n5、补硒养肝 ，补硒能让肝脏中谷胱甘肽过氧化物酶的活性达到正常水平，对养肝护肝起到良好作用，硒麦芽粉、五味子喂成分的养肝片，对养肝护肝起到良好作用调节免疫，对养肝、护肝有良好作用。\\r\\n\\r\\n\\r\\n五、桶状胸：\\r\\n积极治疗原发病，可用外科手术矫正畸形，对症缓解呼吸困难症状。\\r\\n\\r\\n\\r\\n六、心房纤颤：\\r\\n房颤与房扑绝大多数发生于冠心病、高血压性心脏病、肺心病、低血钾急性肺部感染或洋地黄中毒等，因此，应首先查清病因积极进行治疗。一般在房颤或房扑发作前，先出现频繁房早，应予以积极治疗，以防发展为房颤或房扑。对于反复频繁发作者，可摸索适当抗心律失常药物以最小剂量予以长期维持，防止复发。\\r\\n\\r\\n\\r\\n七、佝偻病胸：\\r\\n1、又称漏斗胸，为佝偻病所改变，可见于幼年。\\r\\n2、佝偻病患者，严重影响心肺功能，可进行外科手术治疗。\\r\\n\\r\\n\\r\\n八、胸廓变形：\\r\\n建议胸外科随诊\\r\\n\\r\\n\\r\\n九、胸部局部隆起：\\r\\n建议结合临床参考。\\r\\n\\r\\n\\r\\n十、脊柱畸形引起的胸廓改变：\\r\\n1、严重脊柱畸形所改的胸廓外形改变可引起呼吸、循环障碍。\\r\\n2、建议外科治疗。\\r\\n\\r\\n\\r\\n十一、脊柱畸形所致胸廓改变：\\r\\n供结合临床参考。\\r\\n\\r\\n\\r\\n十二、腹部压痛：\\r\\n建议内科复查,诊治。\\r\\n\\r\\n\\r\\n十三、双肾叩痛：\\r\\n建议进一步内科诊治。\\r\\n\\r\\n\\r\\n十四、肺部听诊干啰音：\\r\\n1、发生于双侧肺部的干啰音，常见于支气管哮喘、慢性支气管炎和心原性哮喘等。\\r\\n2、局部性干啰音，常见于支气管内膜结核或肿瘤等，呼吸内科进一步检查治疗。\\r\\n\\r\\n\\r\\n十五、湿罗音：\\r\\n湿啰音见于支气管炎、支气管扩张、肺部感染，心动能不全等，建议呼吸科进一步检查诊治\\r\\n\\r\\n\\r\\n\",\"conclusion\":\"\",\"summarydoctor\":\"supervisor\",\"summarydate\":\"2018040815:43:36\",\"verifydoctor\":\"\",\"verifydate\":\"\",\"explanation\":null,\"status\":\"1\",\"address\":\"上海卫宁数据科技\",\"phone\":\"\",\"married\":\"已婚\",\"email\":\"\",\"nation\":\"\",\"country\":\"\",\"subreports\":[{\"rowid\":\"4\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018032815:18:19\"},{\"rowid\":\"5\",\"reportname\":\"骨密度测定\",\"reportno\":\"10000302818030031\",\"position\":\"\",\"findings\":\"jcsj\",\"conclusion\":\"jcjl\",\"doctor\":\"\",\"checkdate\":\"2018032815:22:03\"},{\"rowid\":\"6\",\"reportname\":\"动脉硬化检查（体检）\",\"reportno\":\"10000301818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018033114:10:45\"},{\"rowid\":\"8\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018040111:17:23\"},{\"rowid\":\"11\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018040815:43:18\"}],\"reportitems\":[{\"itemid\":\"1\",\"itemname\":\"一般检查\",\"doctor\":\"\",\"checkupdate\":\"20180328\",\"comment\":\"舒张压严重偏高\\r\\n\",\"itemtype\":\"0\",\"disporder\":\"0\",\"itemresults\":[{\"rowid\":\"4637\",\"nodeid\":\"10001\",\"nodename\":\"身高\",\"valuestr\":\"\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"cm\",\"prompt\":\"\",\"disporder\":\"1\"},{\"rowid\":\"4638\",\"nodeid\":\"10002\",\"nodename\":\"体重\",\"valuestr\":\"\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"Kg\",\"prompt\":\"\",\"disporder\":\"2\"},{\"rowid\":\"4639\",\"nodeid\":\"27\",\"nodename\":\"体重指数\",\"valuestr\":\"\",\"rangestd\":\"18.50 ～ 23.90\",\"status\":\"0\",\"unit\":\"\",\"prompt\":\"\",\"disporder\":\"3\"},{\"rowid\":\"4640\",\"nodeid\":\"10005\",\"nodename\":\"收缩压\",\"valuestr\":\"60\",\"rangestd\":\"90 ～ 140\",\"status\":\"0\",\"unit\":\" mmHg\",\"prompt\":\"\",\"disporder\":\"4\"},{\"rowid\":\"4641\",\"nodeid\":\"10006\",\"nodename\":\"舒张压\",\"valuestr\":\"90\",\"rangestd\":\"60.00 ～ 90.00\",\"status\":\"3\",\"unit\":\" mmHg\",\"prompt\":\"↑↑\",\"disporder\":\"5\"}]},{\"itemid\":\"2\",\"itemname\":\"二般检查\",\"doctor\":\"\",\"checkupdate\":\"20180328\",\"comment\":\"舒张压轻度偏高\\r\\n\",\"itemtype\":\"0\",\"disporder\":\"0\",\"itemresults\":[{\"rowid\":\"4637\",\"nodeid\":\"10001\",\"nodename\":\"白细胞浓度\",\"valuestr\":\"\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"ml\",\"prompt\":\"\",\"disporder\":\"1\"},{\"rowid\":\"4638\",\"nodeid\":\"10002\",\"nodename\":\"血压\",\"valuestr\":\"60\",\"rangestd\":\"60.00 ～ 90.00\",\"status\":\"0\",\"unit\":\"KPa\",\"prompt\":\"\",\"disporder\":\"2\"}]}],\"Diseases\":[{\"jbid\":\"4131\",\"jbmc\":\"扁平胸\",\"jbms\":\"\",\"jbjy\":\"扁平胸是由于先天性胸廓发育异常所致，多见于瘦长体型者，不需治疗\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4880\",\"jbmc\":\"上腹部压之不适\",\"jbms\":\"\",\"jbjy\":\"建议内科复查,诊治。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4763\",\"jbmc\":\"肝肿大\",\"jbms\":\"肝肿大可由许多疾病引起是临床上一个重要体征。正常肝脏大小为长径25cmx上下径15cmx前后径16cm。肝脏常可被触及，边缘锐利质软，无压痛。有时肋下触到的肝脏不是由于肝肿大而是由于肝位置下移，此可见于经产妇女腹壁松弛者、歌唱或演奏者横隔运动过分发达肺气肿、有胸腔大量积液、腋下脓肿者有时胆囊肿大、横结肠肿瘤、胰腺囊肿胃癌、右肾下垂、右肾积水右肾囊肿、嗜铬细胞瘤等也可被误认为肝肿大，但呼吸移动度不如肝脏大边缘不如肝脏清晰，故应结合病史、肝脏的位置形态、质地。呼吸移动度有否压痛及其他检查结果来确定病理性肝肿大。\",\"jbjy\":\"1、饮食方面应提供足够的营养，食物要多样化，供给含氨基酸的高价蛋白质、多种维生素、低脂肪、少渣饮食，要防止粗糙多纤维食物损伤食道静脉，引起大出血。 \\r\\n2、血氨偏高或肝功能极差者，应限制蛋白质摄入，以免发生肝昏迷。出现腹水者应进低盐或无盐饮食。 　\\r\\n3、每日测量腹围和测定尿量，腹部肥胖可是自我鉴别脂肪肝的一大方法。 \\r\\n4、注意出血、紫癜、发热、精神神经症状的改变，并及时和医生取得联系。 　\\r\\n5、补硒养肝 ，补硒能让肝脏中谷胱甘肽过氧化物酶的活性达到正常水平，对养肝护肝起到良好作用，硒麦芽粉、五味子喂成分的养肝片，对养肝护肝起到良好作用调节免疫，对养肝、护肝有良好作用。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4476\",\"jbmc\":\"桶状胸\",\"jbms\":\"桶状胸（barrel chest）又称“气肿胸”，指胸廓前后径增加，有时与左右径几乎相等，呈圆桶状，肋骨斜度变小，其与脊柱夹角常大于45°，肋间隙增宽饱满，腹上角增大。见于严重肺气肿患者，亦可见于老年人或矮胖体型者。需治疗原发病，缓解呼吸困难症状，必要时手术矫正畸形。\",\"jbjy\":\"积极治疗原发病，可用外科手术矫正畸形，对症缓解呼吸困难症状。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"}]}}\n";
        str = str.replace("package","baoming");
        ReportResultDTO dto = fromJson(str, ReportResultDTO.class);
        return dto;
    }

    public static ReportResultDTO getDemoDataDetail2() {
        String str = "{\"resultcode\":\"0\",\"reason\":\"success\",\"result\":{\"xh\":\"f804c8da-721c-48ee-a61a-421cd31759ab\",\"tjbh\":\"818030019\",\"orgid\":\"\",\"checkupdate\":\"20180428\",\"name\":\"许同\",\"idcard\":null,\"sex\":\"1\",\"companyname\":\"\",\"examcategory\":\"常规体检\",\"packagename\":\"A体检套餐（男士）\",\"summary\":\"一、一般检查：\\r\\n舒张压严重偏高\\r\\n\\r\\n二、内科：\\r\\n1、扁平胸\\r\\n2、桶状胸\\r\\n3、佝偻病胸\\r\\n4、胸廓变形\\r\\n5、胸部局部隆起\\r\\n6、脊柱畸形引起的胸廓改变\\r\\n7、脊柱畸形所致胸廓改变\\r\\n8、心房纤颤\\r\\n9、A2>P2\\r\\n10、心包摩擦音\\r\\n11、二尖瓣区收缩期Ⅲ级吹风样杂音\\r\\n12、心音A2>P2,第二音亢进\\r\\n13、肺部听诊干啰音\\r\\n14、呼吸音减弱或消失\\r\\n15、肺部实音/伴语音变化\\r\\n16、肺部听诊湿啰音\\r\\n17、肺部捻发音\\r\\n18、哮鸣音\\r\\n19、肺部听诊提示过清音\\r\\n20、肝硬化\\r\\n21、肝肿大\\r\\n22、脾肿大\\r\\n23、上腹部压之不适\\r\\n24、阑尾部压痛\\r\\n25、腹部压痛\\r\\n26、双肾叩痛\\r\\n\\r\\n\",\"advice\":\"一、舒张压严重偏高：\\r\\n1、调整生活方式，限制烟酒，体育煅炼;\\r\\n2、药物剂量维持一段时间以后可以减量观察，如血压仍然能维持在正常水平，能以最低剂量维持血压正常则为理想，但不要轻易停药。\\r\\n\\r\\n\\r\\n二、扁平胸：\\r\\n扁平胸是由于先天性胸廓发育异常所致，多见于瘦长体型者，不需治疗\\r\\n\\r\\n\\r\\n三、上腹部压之不适：\\r\\n建议内科复查,诊治。\\r\\n\\r\\n\\r\\n四、肝肿大：\\r\\n1、饮食方面应提供足够的营养，食物要多样化，供给含氨基酸的高价蛋白质、多种维生素、低脂肪、少渣饮食，要防止粗糙多纤维食物损伤食道静脉，引起大出血。 \\r\\n2、血氨偏高或肝功能极差者，应限制蛋白质摄入，以免发生肝昏迷。出现腹水者应进低盐或无盐饮食。 　\\r\\n3、每日测量腹围和测定尿量，腹部肥胖可是自我鉴别脂肪肝的一大方法。 \\r\\n4、注意出血、紫癜、发热、精神神经症状的改变，并及时和医生取得联系。 　\\r\\n5、补硒养肝 ，补硒能让肝脏中谷胱甘肽过氧化物酶的活性达到正常水平，对养肝护肝起到良好作用，硒麦芽粉、五味子喂成分的养肝片，对养肝护肝起到良好作用调节免疫，对养肝、护肝有良好作用。\\r\\n\\r\\n\\r\\n五、桶状胸：\\r\\n积极治疗原发病，可用外科手术矫正畸形，对症缓解呼吸困难症状。\\r\\n\\r\\n\\r\\n六、心房纤颤：\\r\\n房颤与房扑绝大多数发生于冠心病、高血压性心脏病、肺心病、低血钾急性肺部感染或洋地黄中毒等，因此，应首先查清病因积极进行治疗。一般在房颤或房扑发作前，先出现频繁房早，应予以积极治疗，以防发展为房颤或房扑。对于反复频繁发作者，可摸索适当抗心律失常药物以最小剂量予以长期维持，防止复发。\\r\\n\\r\\n\\r\\n七、佝偻病胸：\\r\\n1、又称漏斗胸，为佝偻病所改变，可见于幼年。\\r\\n2、佝偻病患者，严重影响心肺功能，可进行外科手术治疗。\\r\\n\\r\\n\\r\\n八、胸廓变形：\\r\\n建议胸外科随诊\\r\\n\\r\\n\\r\\n九、胸部局部隆起：\\r\\n建议结合临床参考。\\r\\n\\r\\n\\r\\n十、脊柱畸形引起的胸廓改变：\\r\\n1、严重脊柱畸形所改的胸廓外形改变可引起呼吸、循环障碍。\\r\\n2、建议外科治疗。\\r\\n\\r\\n\\r\\n十一、脊柱畸形所致胸廓改变：\\r\\n供结合临床参考。\\r\\n\\r\\n\\r\\n十二、腹部压痛：\\r\\n建议内科复查,诊治。\\r\\n\\r\\n\\r\\n十三、双肾叩痛：\\r\\n建议进一步内科诊治。\\r\\n\\r\\n\\r\\n十四、肺部听诊干啰音：\\r\\n1、发生于双侧肺部的干啰音，常见于支气管哮喘、慢性支气管炎和心原性哮喘等。\\r\\n2、局部性干啰音，常见于支气管内膜结核或肿瘤等，呼吸内科进一步检查治疗。\\r\\n\\r\\n\\r\\n十五、湿罗音：\\r\\n湿啰音见于支气管炎、支气管扩张、肺部感染，心动能不全等，建议呼吸科进一步检查诊治\\r\\n\\r\\n\\r\\n\",\"conclusion\":\"\",\"summarydoctor\":\"supervisor\",\"summarydate\":\"2018040815:43:36\",\"verifydoctor\":\"\",\"verifydate\":\"\",\"explanation\":null,\"status\":\"1\",\"address\":\"上海卫宁数据科技\",\"phone\":\"\",\"married\":\"已婚\",\"email\":\"\",\"nation\":\"\",\"country\":\"\",\"subreports\":[{\"rowid\":\"4\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018032815:18:19\"},{\"rowid\":\"5\",\"reportname\":\"骨密度测定\",\"reportno\":\"10000302818030031\",\"position\":\"\",\"findings\":\"jcsj\",\"conclusion\":\"jcjl\",\"doctor\":\"\",\"checkdate\":\"2018032815:22:03\"},{\"rowid\":\"6\",\"reportname\":\"动脉硬化检查（体检）\",\"reportno\":\"10000301818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018033114:10:45\"},{\"rowid\":\"8\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018040111:17:23\"},{\"rowid\":\"11\",\"reportname\":\"13碳尿素呼气试验\",\"reportno\":\"10000303818030031\",\"position\":\"\",\"findings\":\"\",\"conclusion\":\"\",\"doctor\":\"\",\"checkdate\":\"2018040815:43:18\"}],\"reportitems\":[{\"itemid\":\"1\",\"itemname\":\"一般检查\",\"doctor\":\"\",\"checkupdate\":\"20180428\",\"comment\":\"舒张压严重偏高\\r\\n\",\"itemtype\":\"0\",\"disporder\":\"0\",\"itemresults\":[{\"rowid\":\"4637\",\"nodeid\":\"10001\",\"nodename\":\"身高\",\"valuestr\":\"183\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"cm\",\"prompt\":\"\",\"disporder\":\"1\"},{\"rowid\":\"4638\",\"nodeid\":\"10002\",\"nodename\":\"体重\",\"valuestr\":\"60\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"Kg\",\"prompt\":\"\",\"disporder\":\"2\"},{\"rowid\":\"4639\",\"nodeid\":\"27\",\"nodename\":\"体重指数\",\"valuestr\":\"\",\"rangestd\":\"18.50 ～ 23.90\",\"status\":\"0\",\"unit\":\"\",\"prompt\":\"\",\"disporder\":\"3\"},{\"rowid\":\"4640\",\"nodeid\":\"10005\",\"nodename\":\"收缩压\",\"valuestr\":\"60\",\"rangestd\":\"90 ～ 140\",\"status\":\"0\",\"unit\":\" mmHg\",\"prompt\":\"\",\"disporder\":\"4\"},{\"rowid\":\"4641\",\"nodeid\":\"10006\",\"nodename\":\"舒张压\",\"valuestr\":\"90\",\"rangestd\":\"60.00 ～ 90.00\",\"status\":\"3\",\"unit\":\" mmHg\",\"prompt\":\"↑↑\",\"disporder\":\"5\"}]},{\"itemid\":\"2\",\"itemname\":\"二般检查\",\"doctor\":\"\",\"checkupdate\":\"20180428\",\"comment\":\"舒张压严重偏高\\r\\n\",\"itemtype\":\"0\",\"disporder\":\"0\",\"itemresults\":[{\"rowid\":\"4637\",\"nodeid\":\"10001\",\"nodename\":\"尿素氮\",\"valuestr\":\"6\",\"rangestd\":\"\",\"status\":\"0\",\"unit\":\"cm\",\"prompt\":\"\",\"disporder\":\"1\"},{\"rowid\":\"4638\",\"nodeid\":\"10002\",\"nodename\":\"氨基酸\",\"valuestr\":\"16\",\"rangestd\":\"18.50 ～ 23.90\",\"status\":\"0\",\"unit\":\"ml\",\"prompt\":\"\",\"disporder\":\"2\"}]}],\"Diseases\":[{\"jbid\":\"4131\",\"jbmc\":\"扁平胸\",\"jbms\":\"\",\"jbjy\":\"扁平胸是由于先天性胸廓发育异常所致，多见于瘦长体型者，不需治疗\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4880\",\"jbmc\":\"上腹部压之不适\",\"jbms\":\"\",\"jbjy\":\"建议内科复查,诊治。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4763\",\"jbmc\":\"肝肿大\",\"jbms\":\"肝肿大可由许多疾病引起是临床上一个重要体征。正常肝脏大小为长径25cmx上下径15cmx前后径16cm。肝脏常可被触及，边缘锐利质软，无压痛。有时肋下触到的肝脏不是由于肝肿大而是由于肝位置下移，此可见于经产妇女腹壁松弛者、歌唱或演奏者横隔运动过分发达肺气肿、有胸腔大量积液、腋下脓肿者有时胆囊肿大、横结肠肿瘤、胰腺囊肿胃癌、右肾下垂、右肾积水右肾囊肿、嗜铬细胞瘤等也可被误认为肝肿大，但呼吸移动度不如肝脏大边缘不如肝脏清晰，故应结合病史、肝脏的位置形态、质地。呼吸移动度有否压痛及其他检查结果来确定病理性肝肿大。\",\"jbjy\":\"1、饮食方面应提供足够的营养，食物要多样化，供给含氨基酸的高价蛋白质、多种维生素、低脂肪、少渣饮食，要防止粗糙多纤维食物损伤食道静脉，引起大出血。 \\r\\n2、血氨偏高或肝功能极差者，应限制蛋白质摄入，以免发生肝昏迷。出现腹水者应进低盐或无盐饮食。 　\\r\\n3、每日测量腹围和测定尿量，腹部肥胖可是自我鉴别脂肪肝的一大方法。 \\r\\n4、注意出血、紫癜、发热、精神神经症状的改变，并及时和医生取得联系。 　\\r\\n5、补硒养肝 ，补硒能让肝脏中谷胱甘肽过氧化物酶的活性达到正常水平，对养肝护肝起到良好作用，硒麦芽粉、五味子喂成分的养肝片，对养肝护肝起到良好作用调节免疫，对养肝、护肝有良好作用。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"},{\"jbid\":\"4476\",\"jbmc\":\"桶状胸\",\"jbms\":\"桶状胸（barrel chest）又称“气肿胸”，指胸廓前后径增加，有时与左右径几乎相等，呈圆桶状，肋骨斜度变小，其与脊柱夹角常大于45°，肋间隙增宽饱满，腹上角增大。见于严重肺气肿患者，亦可见于老年人或矮胖体型者。需治疗原发病，缓解呼吸困难症状，必要时手术矫正畸形。\",\"jbjy\":\"积极治疗原发病，可用外科手术矫正畸形，对症缓解呼吸困难症状。\\r\\n\",\"jyksdm\":\"\",\"jyksmc\":\"\"}]}}\n";
        str = str.replace("package","baoming");
        ReportResultDTO dto = fromJson(str, ReportResultDTO.class);
        return dto;
    }
}
