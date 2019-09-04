package com.broker.publisher;

import com.broker.common.Message;

public interface Publisher {

	public boolean publishMessage(String topicName, Message message);
}