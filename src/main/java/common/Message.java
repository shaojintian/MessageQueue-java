package common;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import util.JsonUtil;

import java.nio.charset.Charset;

/** super class **/
@Slf4j
@Data
public abstract class Message <T extends MessageBody>{

    private MessageHeader messageHeader;
    private T messageBody;

    /** encode message **/
    public void encode(ByteBuf byteBuf){
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeLong(messageHeader.getStreamID());
        //body
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());

    }

    /** method **/
    public abstract Class<T>  getMessageBodyDecoderClass(int opCode);

    /** decode message**/
    public void decode(ByteBuf msg){
        int version  = msg.readInt();
        int opCode = msg.readInt();
        long streamID = msg.readLong();

        //package msgHeader
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setVersion(version);
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamID(streamID);
        this.messageHeader = messageHeader;

        //get msgBody
        Class<T> clazz = getMessageBodyDecoderClass(opCode);
        T msgBody = JsonUtil.fromJson(msg.toString(Charset.forName("utf-8")),clazz);
        this.messageBody = msgBody;






    }




}
