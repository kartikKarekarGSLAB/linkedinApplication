package com.gslab.linkedin.linkedindemo.validator;

import java.util.regex.Pattern;

public class UserValidator {
	public static boolean validateUserName(String username) {
		return  Pattern.matches("^[a-zA-Z0-9_-]{6,19}$", username); 
	}
	public static boolean validatePassword(String password) {
		return  Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,19}$", password);
	}
	public static boolean validateEmail(String email) {
		return Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", email);
	}
}
