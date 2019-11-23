package common;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import util.JsonUtil;

/** super class **/
@Data
public abstract class Message <T extends MessageBody>{

    private MessageHeader messageHeader;
    private T messageBody;

    /** encode message **/
    public void encode(ByteBuf byteBuf){
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeLong(messageHeader.getStreamID());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());

    }

    /** method **/
    public abstract Class<T>  getMessageBodyDecoderClass(int opCode);

    /** decode message**/
    public void decode(ByteBuf msg){
        int version  = msg.readInt();
        int opCode = msg.readInt();
        long streamID = msg.readLong();

        //get msg body




    }




}
