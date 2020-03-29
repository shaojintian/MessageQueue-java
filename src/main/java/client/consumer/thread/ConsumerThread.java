package client.consumer.thread;

import client.consumer.IConsumer;

public class ConsumerThread {

    private IConsumer consumer;

    public ConsumerThread(IConsumer consumer){
        this.consumer= consumer;
    }
}
