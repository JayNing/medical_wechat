package com.zx.common.util;

import java.lang.reflect.Type;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.sf.json.JSONObject;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:31:10
 * //TODO Gson工具类
 */
public class GsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(GsonUtil.class);

    private static Gson gson = new Gson();

    private GsonUtil() {
    }

    private static GsonBuilder builder = new GsonBuilder();

    static {
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        gson = builder.create();
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

    /**
     *jsonStrToJson
     *2016年6月28日 上午11:11:50
     *@param jsonStr
     *@return
     *TODO：将json字符串转换成json对象
     */
    public static JSONObject jsonStrToJson(String jsonStr) {
        if (StringUtil.notEmpty(jsonStr)) {
            JSONObject object = JSONObject.fromObject(jsonStr);
            return object;
        }
        return null;
    }

    public static void main(String[] args) {
        /* String floorMapData = "[{$uuid$:$8630f816-b2d1-11e6-8a28-6c0b84672806$,$buildingId$:$249$,$floorNo$:$Floor1$,$jpgWidth$:419,$jpgHeight$:268,$pointX$:0,$pointY$:0,$scaleX$:1,$scaleY$:1,$numInFloor$:0,$createBy$:$test$,$updateBy$:$test$,$floorNoName$:$cc0$,$pointXName$:$dd0$,$pointYName$:$ee0$,$scaleXName$:$ff0$,$scaleYName$:$gg0$,$numInFloorName$:$hh0$},{$floorNo$:$Floo2$,$pointX$:0,$pointY$:0,$scaleX$:$2$,$scaleY$:$2$,$numInFloor$:0,$changed$:true,$floorNoName$:$c1$,$pointXName$:$d1$,$pointYName$:$e1$,$scaleXName$:$f1$,$scaleYName$:$g1$,$numInFloorName$:$h1$}]";
        floorMapData = floorMapData.replace("$", "\"");
        System.out.println(floorMapData);*/
        //String str = "[{\"battery\":0,\"buildingId\":\"403\",\"compensationPower\":0,\"createBy\":\"test\",\"createTime\":\"2017-01-19\",\"deviceId\":\"00000000\",\"deviceMac\":\"032156478965\",\"deviceType\":\"ap\",\"deviceUuid\":\"\",\"errorCode\":0,\"floorNo\":\"FloorB1\",\"ibeaconX\":561355,\"ibeaconY\":209898,\"immediate\":0,\"light\":0,\"major\":0,\"measurePower\":0,\"minor\":0,\"near\":0,\"reliability\":0,\"rssi\":0,\"shopId\":0,\"temperature\":0,\"txPower\":0,\"updateBy\":\"test\",\"updateTime\":\"2017-01-19\",\"userId\":\"a2072d12-a4d0-11e6-8a28-6c0b84672806\",\"uuid\":\"9cc7bc46-ddeb-11e6-86fb-000c292cc9f7\"},{\"battery\":0,\"buildingId\":\"403\",\"compensationPower\":106060,\"createBy\":\"test\",\"createTime\":\"2017-01-19\",\"deviceId\":\"FDA50693A4E24FB1AFCFC6EB0764782500220001\",\"deviceMac\":\"563214563214\",\"deviceType\":\"ibeacon\",\"deviceUuid\":\"FDA50693-A4E2-4FB1-AFCF-C6EB07647825\",\"errorCode\":0,\"floorNo\":\"FloorB1\",\"ibeaconX\":625004,\"ibeaconY\":392033,\"immediate\":0,\"light\":0,\"major\":34,\"measurePower\":0,\"minor\":1,\"near\":0,\"reliability\":0,\"rssi\":0,\"shopId\":0,\"temperature\":0,\"txPower\":0,\"updateBy\":\"test\",\"updateTime\":\"2017-01-19\",\"userId\":\"a2072d12-a4d0-11e6-8a28-6c0b84672806\",\"uuid\":\"d09640f4-dde8-11e6-86fb-000c292cc9f7\"},{\"battery\":0,\"buildingId\":\"403\",\"compensationPower\":0,\"createBy\":\"test\",\"createTime\":\"2017-01-19\",\"deviceId\":\"00000000\",\"deviceMac\":\"987654321010\",\"deviceType\":\"ap\",\"deviceUuid\":\"\",\"errorCode\":0,\"floorNo\":\"Floor2\",\"ibeaconX\":46435,\"ibeaconY\":72571,\"immediate\":0,\"light\":0,\"major\":0,\"measurePower\":0,\"minor\":0,\"near\":0,\"reliability\":0,\"rssi\":0,\"shopId\":0,\"temperature\":0,\"txPower\":0,\"updateBy\":\"test\",\"updateTime\":\"2017-01-19\",\"userId\":\"a2072d12-a4d0-11e6-8a28-6c0b84672806\",\"uuid\":\"fba182cc-dde8-11e6-86fb-000c292cc9f7\"}]";
        // List<Ibeacon> beaconList = GsonUtil.getInstance().fromJson(str, new TypeToken<List<Ibeacon>>() {}.getType());
        //System.out.println(beaconList);
        // JSONArray myJsonArray = JSONArray.fromObject(str); 
        //   List<BuildingFloorMap> list = new Gson().fromJson(floorMapData, new TypeToken<List<BuildingFloorMap>>() {}.getType());
        // System.out.println(myJsonArray);
        // Ibeacon[] stus = (Ibeacon[]) JSONArray.toArray(myJsonArray, Ibeacon.class); 
        // System.out.println(stus);
    }

}
