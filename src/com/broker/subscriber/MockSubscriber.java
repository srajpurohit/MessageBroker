package com.broker.subscriber;

import java.util.Observable;
import java.util.Observer;

import com.broker.common.Message;

public class MockSubscriber implements Subscriber, Observer {
	public String subscriberName;

	public MockSubscriber(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	@Override
	public void update(Observable o, Object arg) {
		onMessage((Message) arg);
	}

	@Override
	public boolean subscribe(String topicName) {
		return false;
	}

	@Override
	public void onMessage(Message message) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(subscriberName + " Consumed Message " + message);
	}
}