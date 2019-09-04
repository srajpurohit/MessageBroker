package com.broker.publisher;

import com.broker.common.Message;
import com.broker.messagebroker.*;

public class MockPublisher implements Publisher {
	Broker broker;

	public MockPublisher(Broker broker) {
		this.broker = broker;
	}

	@Override
	public boolean publishMessage(String topicName, Message message) {
		return broker.publishMessage(topicName, message);
	}
}