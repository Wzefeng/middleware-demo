package demo.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_quick_consumer_name");
        consumer.setNamesrvAddr(Consts.NAME_SER_ADDR);
        // 设置消费位移为最新位移
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 订阅 topic
        consumer.subscribe("test_quick_topic", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            MessageExt messageExt = msgs.get(0);
            try {
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String keys = messageExt.getKeys();
                String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                if ("key3".equals(keys)) {
                    throw new RuntimeException("For purpose");
                }

                System.out.printf("消费消息topic=%s, tags=%s, keys=%s, body=%s\n", topic, tags, keys, body);

            } catch (Exception e) {
                e.printStackTrace();
                int reconsumeTimes = messageExt.getReconsumeTimes();
                // 重复消费次数 >= 3次，放弃消费（日志记录/业务补偿/告警）
                if (reconsumeTimes >= 3) {
                    String body;
                    try {
                        body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    } catch (UnsupportedEncodingException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.err.println("log error message:" + body);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
        System.out.println("RocketMQ consumer starting...");
    }

}
