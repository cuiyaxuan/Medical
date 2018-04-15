package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月15日 00:40
 **/
public class DateUtils {
    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
