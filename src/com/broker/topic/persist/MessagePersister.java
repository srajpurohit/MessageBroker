package com.broker.topic.persist;

import java.util.List;

import com.broker.common.Message;

public interface MessagePersister {
	public boolean persistMessage(Message message);

	public boolean deleteMessage(Message message);

	public List<Message> readOldMessages();
}