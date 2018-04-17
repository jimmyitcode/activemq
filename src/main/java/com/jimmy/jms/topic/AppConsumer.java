package com.jimmy.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息订阅者
 * @author 王志明
 * @create 2018-03-22 上午 11:53
 **/
public class AppConsumer {
    private static final String url = "tcp://127.0.0.1:61616";
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws JMSException {
        //1创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2创建连接
        Connection connection = connectionFactory.createConnection();
        //3启动连接
        connection.start();
        //4创建会话
        Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
        //5创建目标
        Destination destination = session.createTopic(topicName);
        //6创建消息者
        MessageConsumer consumer =  session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收的消息是"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
