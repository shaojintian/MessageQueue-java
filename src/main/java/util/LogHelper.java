package util;

import java.text.MessageFormat;
import java.util.Date;

public class LogHelper {

    private static final String LOG_TEMPLATE = "<br><br>  <span style=\"color:#00c0ef;\" > >>>>>>>>>>> {0} <<<<<<<<<<< </span>  <br>时间：{1}  <br>备注：{2}";

    /**
     * initialize log
     * @return log
     */
    public static String initLog(String logTitle ,String logContent){

        String tim = DateUtil.formatDateTime(new Date());

        String log = MessageFormat.format(LOG_TEMPLATE, tim,logTitle,logContent);

        //ignore
        if(log.length()>20000){
            log = log.substring(0, 20000)+ "...";
        }

        return log;
    }
}

