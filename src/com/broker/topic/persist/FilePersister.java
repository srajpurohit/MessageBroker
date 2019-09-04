package com.broker.topic.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.broker.common.Message;

public class FilePersister implements MessagePersister {
	public final String homeDirectory = "c:\\messages\\";
	String topicName;

	public FilePersister(String topicName) {
		this.topicName = topicName;
	}

	@Override
	public boolean persistMessage(Message message) {
		ObjectOutputStream output;
		try {
			if (!new File(homeDirectory + topicName).exists()) {
				new File(homeDirectory + topicName).mkdir();
			}

			output = new ObjectOutputStream(
					new FileOutputStream(homeDirectory + topicName + "\\" + message.hashCode()));
			output.writeObject(message);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteMessage(Message message) {
		if (new File(homeDirectory + topicName + "\\" + message.hashCode()).exists()) {
			return new File(homeDirectory + topicName + "\\" + message.hashCode()).delete();
		}
		return false;
	}

	@Override
	public List<Message> readOldMessages() {
		List<Message> messages = new ArrayList<Message>();
		File folder = new File(homeDirectory + topicName);

		if (folder.exists()) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isFile()) {
					try {
						ObjectInputStream instream = new ObjectInputStream(
								new FileInputStream(fileEntry.getAbsolutePath()));
						try {
							Message message = (Message) instream.readObject();
							messages.add(message);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						instream.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return messages;
	}
}