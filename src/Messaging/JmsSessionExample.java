package Messaging;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsSessionExample {
    public static void main(String[] args) throws Exception {
        // Connection Factory --Creates a connection to the JMS provider.
        //he ConnectionFactory is a tool provided by the messaging service (like ActiveMQ) to create connections.
        //iff JMS is the process of sending and receiving messages (like letters),
        // ActiveMQ is the post office that delivers those messages between different parties.
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a Connection
        Connection connection = factory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a Destination (Queue or Topic)
        Destination queue = session.createQueue("MyQueue");

        // Create a Producer
        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage("Hello, JMS!");
        producer.send(message);

        // Create a Consumer
        MessageConsumer consumer = session.createConsumer(queue);
        Message receivedMessage = consumer.receive();

        if (receivedMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) receivedMessage;
            System.out.println("Received: " + textMessage.getText());
        }

        // Cleanup
        producer.close();
        consumer.close();
        session.close();
        connection.close();
    }
}
