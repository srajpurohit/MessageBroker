package com.broker.common;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String messaage;

	public String getMessaage() {
		return messaage;
	}

	public Message(String message) {

		this.messaage = message;
	}

	public void setMessaage(String messaage) {
		this.messaage = messaage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messaage == null) ? 0 : messaage.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Message [messaage=" + messaage + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (messaage == null) {
			if (other.messaage != null)
				return false;
		} else if (!messaage.equals(other.messaage))
			return false;
		return true;
	}
}