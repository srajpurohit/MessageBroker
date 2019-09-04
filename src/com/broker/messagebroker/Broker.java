package com.broker.messagebroker;

import com.broker.common.Message;
import com.broker.topic.Topic;

public interface Broker {

	public boolean publishMessage(String topicName, Message message);

	public boolean addTopic(Topic topic);

	public boolean recoverMessage();

	public boolean isInitialized();

	public void setInitialized();
}