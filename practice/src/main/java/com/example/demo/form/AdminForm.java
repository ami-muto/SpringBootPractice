package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminForm {
	@NotBlank(message = "姓は必須です")
	private String lastName;
	
	@NotBlank(message = "名は必須です")
	private String firstName;
	
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;
	
	@NotBlank(message = "パスワードは必須です")
	@Size(min = 6, message = "パスワードは６文字以上で入力してください")
	private String password;

	public String getLastName() {
		return lastName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public String getFirstName() {
		return firstName;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getPassword() {
		return password;

	}

	public void setPassword(String password) {
		this.password = password;

	}

}
