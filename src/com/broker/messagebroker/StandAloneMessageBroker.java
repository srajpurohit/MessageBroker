package com.broker.messagebroker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.broker.common.Message;
import com.broker.topic.Topic;

public class StandAloneMessageBroker implements Broker {
	Map<String, Topic> topics = new HashMap<String, Topic>();
	private boolean initiaized = false;

	@Override
	public boolean publishMessage(String topicName, Message message) {
		Topic topic = topics.get(topicName);
		return topic.publishMessage(message);
	}

	public boolean addTopic(Topic topic) {
		if (!topics.containsKey(topic.getTopicName())) {
			topics.put(topic.getTopicName(), topic);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean recoverMessage() {
		if (initiaized) {
			for (Map.Entry<String, Topic> entry : topics.entrySet()) {
				List<Message> messages = entry.getValue().getLastMessages();
				if (!messages.isEmpty()) {
					for (Message mess : messages) {
						System.out.println(
								" Last message Resend " + mess + " to topic " + entry.getValue().getTopicName());
						this.publishMessage(entry.getValue().getTopicName(), mess);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isInitialized() {
		return initiaized;
	}

	@Override
	public void setInitialized() {
		initiaized = true;
	}
}