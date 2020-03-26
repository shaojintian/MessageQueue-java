package client.producer;

import java.util.Set;
import java.util.Date;
import client.message.Message;


public class Producer {
    /**
     * take message
     */
    public static void takeMessage(Message message){
        //check arg security
        if(message==null){
            throw new IllegalArgumentException("message can not be null");
        }



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
