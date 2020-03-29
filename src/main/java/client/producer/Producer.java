package client.producer;

import java.util.Set;
import java.util.Date;
import client.message.Message;
import client.consumer.Consumer;
import client.message.MessageStatus;
import util.IpUtil;
import util.LogHelper;


public class Producer {
    /**
     * take message to make it valid and regular
     */
    public static void makeMessageRegular(Message message){
        //check arg security
        if(message==null){
            throw new IllegalArgumentException("message can not be null");
        }

        //topic
        if (message.getTopic()==null||message.getTopic().trim().length()==0){
            throw new IllegalArgumentException("message topic can not be empty");
        }

        if (message.getTopic().length()<4 || message.getTopic().length()>255){
            throw new IllegalArgumentException("message topic length must in [4,255]");
        }

        //group
        if(message.getGroup()==null||message.getGroup().trim().length()==0){   //""
            message.setGroup(Consumer.DEFAULT_GROUP);
        }

        if(message.getGroup().length()<4||message.getGroup().length()>255){
            throw new IllegalArgumentException("message group length must in [4,255]");
        }
        //data
        if (message.getData()==null){
            message.setData("");
        }
        if(message.getData().length()>20000){
            throw new IllegalArgumentException("message data length valid in [0,20,000]");
        }
        //status
        message.setStatus(MessageStatus.NEW.name());
        //retryCount
        if(message.getRetryCount()<0){
            message.setRetryCount(0);
        }

        //shardingID
        if(message.getShardingID()<0){
            message.setShardingID(0);
        }

        //effectTime
        if(message.getEffectTime()==null){
            message.setEffectTime(new Date());
        }
        //timeout
        if(message.getTimeout()<0){
            message.setTimeout(0);
        }

        //log
        String appendLog = LogHelper.initLog("Produce message",
                                           "producer ID =" + IpUtil.getIp());
        message.setLog(appendLog);


    }

    /**
     * do produce
     */
    public static void doProduce(Message message,boolean async){

        makeMessageRegular(message);


    }

    public static void doProduce(Message message){doProduce(message, true);}

    /**
     * broadcast produce
     */
    public static void broadcast(Message message,boolean async){

    }

    public static void broadcast(Message message){
        broadcast(message, true);
    }

}
