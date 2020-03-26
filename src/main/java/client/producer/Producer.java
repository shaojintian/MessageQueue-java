package client.producer;

import java.util.Set;
import java.util.Date;
import client.message.Message;
import client.consumer.Consumer;


public class Producer {
    /**
     * take message
     */
    public static void takeMessage(Message message){
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
        //data

        //status

        //retryCount



    }

    /**
     * do produce
     */
    public static void doProduce(Message message,boolean async){

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
