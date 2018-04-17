package com.jimmy.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息发布者(主题模式)
 * @author 王志明
 * @create 2018-03-22 上午 12:21
 **/
public class AppProducer {
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
        //6创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 100; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("text"+i);
            producer.send(textMessage);
            System.out.println("发送消息"+textMessage.getText());
        }
        connection.close();
    }
}
