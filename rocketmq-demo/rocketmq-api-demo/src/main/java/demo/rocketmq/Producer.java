package demo.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("test_quick_producer_name");
        producer.setNamesrvAddr(Consts.NAME_SER_ADDR);

        producer.start();

        for (int i = 1; i <= 5; i++) {
            Message message = new Message("test_quick_topic", "TagA", "key" + i, ("Hello RocketMQ-" + i).getBytes());
            SendResult sendResult = producer.send(message);
            System.out.println("消息发出: " + sendResult);
        }

        producer.shutdown();
    }

}
