package com.sincosmos;

import ch.hsr.geohash.GeoHash;
import ch.hsr.geohash.WGS84Point;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //regex();
        //System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM")));
        //1577254069
        //1577254049689
        //String couponCode = "AF1200R100SFc2Idelpt";
        //String couponCode = "CD80fadfadf";
        //String couponCode = "CD75fadfadf";
        String couponCode = "BX75fadfadf";
        Pattern pattern = Pattern.compile("^([A-Z])([A-Z])(\\d+)([A-Z]*)(\\d*)(.*)");
        Matcher matcher = pattern.matcher(couponCode);
        if(matcher.matches()){
            System.out.println(CouponTypeMap.getByCode(matcher.group(1)).getType());
            String subType = matcher.group(2);
            System.out.println(subType);
            if("X".equals(subType)){
                System.out.println("固定金额券");
                System.out.println("固定优惠" + matcher.group(3) + "元");
            }else if("F".equals(subType)){
                System.out.println("满减券");
                System.out.println("满" + matcher.group(3) + "减" + matcher.group(5));
            }else if("D".equals(subType)){
                System.out.println("折扣券");
                System.out.println(matcher.group(3).replaceAll("0", "") + "折");
            }else{}
        }

        int a = 1;
        double b = 2.;
        double c = 00.555;
        String d = "0011.33";
        double e = Double.parseDouble(d);

        System.out.println(Pattern.matches("\\d+\\.?\\d{0,2}", String.valueOf(e)));
        String input = "0011.33";
        System.out.println(input.length() - input.indexOf(".") - 1);
    }

    private static void regex(){
        String ua = "Mozilla/5.0 (iPhone; CPU iPhone OS 5_1_1 like Mac OS X) " +
                "AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9B206 Safari/7534.48.3" +
                " __weio__906.5a test";

        Pattern versionPattern = Pattern.compile(".*__weibo__(([0-9a-zA-Z]+\\.)*[0-9a-zA-Z]+).*");
        Matcher matcher = versionPattern.matcher(ua.toLowerCase());
        if(matcher.matches()){
            // 如果 ua 中包含版本号信息，将其转化为 hex 大写字符串
            StringBuilder sb = new StringBuilder();
            System.out.println("weibo client version from User-Agent:: " + matcher.group(1));
            for(String s : matcher.group(1).split("\\.")){
                System.out.println(s);
                sb.append(Integer.toHexString(Integer.valueOf(s)).toUpperCase());
                //sb.append(Hex.encodeHexString(Charset.forName("UTF-8").encode(s), false));
            }
            System.out.println("weibo client version from User-Agent to hex:: " + sb.toString());
        }

        String prods = "1|3|5";
        System.out.println(Arrays.asList(prods.split("|")).contains("3"));
    }


    /**
     * 计算两 GPS 点之间的距离
     * @param p1 gps 点
     * @param p2 gps 点
     * @return 两点之间的距离，单位为米
     */
    private double distance(WGS84Point p1, WGS84Point p2){
        return distance(p1.getLatitude(), p2.getLatitude(),
                p1.getLongitude(), p2.getLongitude(), 0.0, 0.0);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point
     * lat2, lon2 End point
     * el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public double distance(double lat1, double lat2, double lon1,
                            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}

enum CouponTypeMap {
    //A-全场通用券，B-粉丝券，C-非粉通用券，D-非粉兴趣券，E-非粉商圈券，F-非粉关系券，G-非粉搜索券，
    //
    //H-指定账号券，I-代投券，J-涨粉券。（此字段最多支持61种类型，S不在取值范围）
    GENERAL("A", "全场通用券"),
    FANS("B", "粉丝券"),
    NONFANSGENERAL("C", "非粉通用券"),
    NONFANSINTEREST("D", "非粉兴趣券"),
    NONFANSTRADE("E", "非粉商圈券"),
    NONFANSRELATION("F", "非粉关系券"),
    NONFANSSEARCH("G", "非粉搜索券"),
    DESIGNATE("H", "指定账号券"),
    AGENT("I", "代投券"),
    FANSADD("J", "涨粉券");

    private String code;
    private String type;

    CouponTypeMap(String code, String type) {
        this.code = code;
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public String getType() {
        return type;
    }

    public static CouponTypeMap getByCode(String typeCode){
        for(CouponTypeMap c: CouponTypeMap.values()){
            if( c.getCode().equals(typeCode) ){
                return c;
            }
        }
        return CouponTypeMap.AGENT;
    }
}

