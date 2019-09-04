package com.broker.subscriber;

import com.broker.common.Message;

public interface Subscriber {
	public boolean subscribe(String topicName);

	public void onMessage(Message message);
}