package com.broker.topic;

import java.util.List;

import com.broker.common.Message;
import com.broker.subscriber.Subscriber;

public interface Topic {
	public boolean publishMessage(Message message);

	public String getTopicName();

	public void addSubscriber(Subscriber subscriber);

	public List<Message> getLastMessages();
}