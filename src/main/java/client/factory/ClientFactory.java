package client.factory;

import client.consumer.annotation.Consumer;
import client.consumer.thread.ConsumerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import client.consumer.IConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.lang.reflect.*;

/**
 * @author sjt 2020-03-29 17:39:55
 */
public class ClientFactory {

    private  static final Logger logger = LoggerFactory.getLogger(ClientFactory.class);

    //--------------------attributes---------------------------
    private String adminAddress;
    private String accessToken;
    private List<IConsumer> consumerList ;
    private ArrayList<ConsumerThread>  consumerThreads;

    public void setAdminAddress(String adminAddress){this.adminAddress = adminAddress};

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setConsumerList(List<IConsumer> consumerList) {
        this.consumerList = consumerList;
    }

    //--------------------initialize & destroy functions---------

    public void initFactory(){

        //pre : valid consumer
        makeConsumerValid();

        //startBrokerService
        startBrokerService();

    }



    // -----------------valid consumer-------------
    private void makeConsumerValid(){

        //check
        if(consumerList==null || consumerList.size()==0){
            logger.warn(">>>>>>>>>>>>> mq Consumer Not Found.");
            return;
        }

        //consumer thread
        for(IConsumer consumer : consumerList){

            //valid consumer
            Consumer annotation = consumer.getClass().getAnnotation(Consumer.class);
            if (annotation == null){
                throw new RuntimeException("mq-java, Consumer ("+ consumer.getClass() +") annotation not found ");
            }

            //valid group
            if(annotation.group()==null || annotation.group().trim().length()==0){

                try {
                    // annotation memberValues
                    InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                    Field mValField = invocationHandler.getClass().getDeclaredField("memberValues");
                    mValField.setAccessible(true);
                    Map memberValues = (Map) mValField.get(invocationHandler);

                    //set data for field "group"
                    String randomGroup = UUID.randomUUID().toString().replaceAll("-", "");
                    memberValues.put("group", randomGroup);
                }catch (Exception e){
                    throw new RuntimeException("mq-java, Consumer ("+ consumer.getClass() +") group UUID generate error and group empty ");

                }
            }
            //check whether group is set to succeed
            if(annotation.group()==null||annotation.group().trim().length()==0){
                throw new RuntimeException("mq-java, Consumer ("+ consumer.getClass() +") group is empty after setting it  ");
            }

            //check topic
            if(annotation.topic()==null||annotation.topic().trim().length()==0){
                throw new RuntimeException("mq-java, Consumer ("+ consumer.getClass() +") topic is empty");
            }

            //consumer threads
            consumerThreads.add(new ConsumerThread(consumer));

        }

    }

    private void startBrokerService(){

    }

    private void startConsumer(){

    }

    private void destroyConsumer(){

    }

    private void destroyBrokerService(){

    }

}
