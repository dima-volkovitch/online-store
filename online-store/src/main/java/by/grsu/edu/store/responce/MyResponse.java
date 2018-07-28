package by.grsu.edu.store.responce;

public class MyResponse {
	public static final String ACCEPTED = "Your response is ACCEPTED!";

	private Boolean isAccepted = true;

	private Object value;

	private String message = ACCEPTED;

	public MyResponse() {

	}

	public MyResponse(Object value) {
		this.value = value;
	}

	public MyResponse(Object value, String message) {
		this.value = value;
		this.message = message;
	}

	public MyResponse(String message, Boolean isAccepted) {
		this.message = message;
		this.isAccepted = isAccepted;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String token) {
		this.message = token;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
}
