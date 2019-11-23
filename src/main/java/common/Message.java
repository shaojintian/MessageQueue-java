package common;

import lombok.Data;

@Data
public abstract class Message <T extends MessageBody>{

    private MessageHeader messageHeader;
    private T messageBody;
`wda`

}
