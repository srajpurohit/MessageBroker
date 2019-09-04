package com.broker.main;

import com.broker.common.Message;
import com.broker.messagebroker.Broker;
import com.broker.messagebroker.StandAloneMessageBroker;
import com.broker.publisher.MockPublisher;
import com.broker.publisher.Publisher;
import com.broker.subscriber.MockSubscriber;
import com.broker.subscriber.Subscriber;
import com.broker.topic.PersistentTopic;
import com.broker.topic.Topic;

public class BrokerTester {
	Broker broker;
	Subscriber sub1;
	Subscriber sub2;
	Topic topic1;
	Topic topic2;

	Publisher pub1;
	Publisher pub2;

	public static void main(String args[]) {
		BrokerTester test = new BrokerTester();
		test.initialize();

		test.broker.publishMessage("topic1", new Message("my test message"));
		test.broker.publishMessage("topic2", new Message("my test2 message"));

		test.broker.recoverMessage();
	}

	public void initialize() {
		broker = new StandAloneMessageBroker();
		;
		topic1 = new PersistentTopic("topic1");
		topic2 = new PersistentTopic("topic2");

		sub1 = new MockSubscriber("sub1");
		sub2 = new MockSubscriber("sub2");

		topic1.addSubscriber(sub1);
		topic2.addSubscriber(sub2);
		topic2.addSubscriber(sub1);

		broker.addTopic(topic1);
		broker.addTopic(topic2);

		pub1 = new MockPublisher(broker);
		pub2 = new MockPublisher(broker);

		broker.setInitialized();
	}
}