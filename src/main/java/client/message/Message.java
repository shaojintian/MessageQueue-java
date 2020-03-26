package client.message;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionID = 42L;

    private long id;            //message ID
    private String topic;       //message topic
    private String group;       //message group,
    private String data;        //message data
    private String status;      //message status in MessageStatus.java
    private int retryCount;     //message retry count ,when it >  0 ,retry send message ,then retryCount-1
    private long shardingID;    //when it>0 ,it works, else message ID instead.
                                //consumer use this to consume messages in sharding
                                //when different shardingID , consume concomitantly,else consume in serial order

    private int timeout;        //unit:second. when it >0,it works. Mark failed positively, when over timeout
    private Date effectTime;    //work after effectTime,but work immediately use "new Date()"
    private Date initTime;      //create message time
    private String log;         //message flow log


    /**
     * constructors
     */
    public Message(){

    }

    public Message(String topic,String data){
        this.topic=topic;
        this.data=data;
    }

    public Message(String topic,String data,Date effectTime){
        this.topic=topic;
        this.data=data;
        this.effectTime = effectTime;
    }

    public Message(String topic,String data,long shardingID){
        this.topic=topic;
        this.data=data;
        this.shardingID = shardingID;
    }

    //clone message
    public Message(Message message) {
        this.id = message.id;
        this.topic = message.topic;
        this.group = message.group;
        this.data = message.data;
        this.status = message.status;
        this.retryCount = message.retryCount;
        this.shardingID = message.shardingID;
        this.timeout = message.timeout;
        this.effectTime = message.effectTime;
        this.initTime = message.initTime;
        this.log = message.log;
    }














}
