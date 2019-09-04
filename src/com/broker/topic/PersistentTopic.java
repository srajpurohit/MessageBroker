package com.broker.topic;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.broker.common.Message;
import com.broker.subscriber.Subscriber;
import com.broker.topic.persist.FilePersister;
import com.broker.topic.persist.MessagePersister;

public class PersistentTopic extends Observable implements Topic {
	private String topicName;
	MessagePersister pesister;

	public PersistentTopic(String topicName) {
		super();
		this.topicName = topicName;
		this.pesister = new FilePersister(topicName);
	}

	public boolean publishMessage(Message message) {
		System.out.println("  publishing messaage ");

		pesister.persistMessage(message);
		setChanged();
		this.notifyObservers(message);
		pesister.deleteMessage(message);
		return true;
	}

	@Override
	public String getTopicName() {
		return topicName;
	}

	@Override
	public void addSubscriber(Subscriber subscriber) {
		addObserver((Observer) subscriber);
	}

	public void persistentMessage(Message messaage) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Message> getLastMessages() {
		return pesister.readOldMessages();
	}
}