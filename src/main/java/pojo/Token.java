package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
	private String userName;
	private String password;

	public String getUsername() {
		return userName;
	}

	public Token setUsername(String username) {
		this.userName = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Token setPassword(String password) {
		this.password = password;
		return this;
	}
}
