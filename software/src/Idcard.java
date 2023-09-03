//import com.baidu.ai.aip.utils.Base64Util;

import java.net.URLEncoder;
import java.util.Base64;
import lib2.Base64Util;
import lib2.FileUtil;
import lib2.HttpUtil;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * 身份证识别
 */
public class Idcard {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static JSONObject idcard(String s) {
        JSONObject jb1=null;
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            // 本地文件路径

            byte[] imgData = FileUtil.readFileByBytes(s);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "id_card_side=" + "front" + "&image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.413b9cf6baaeef67fb17a817a319a8eb.2592000.1670299790.282335-28266649";

            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = new JSONObject(result);
             jb1=jsonObject.getJSONObject("words_result");
            return jb1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}