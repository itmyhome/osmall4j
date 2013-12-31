//package com.os.web;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.regex.Pattern;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.os.domain.User;
//
//public class UserModelValidator implements Validator {
//	private static final Pattern USERNAME_PATTERN = Pattern
//			.compile("[a-zA-Z]\\w{4,19}");
//	private static final Pattern PASSWORD_PATTERN = Pattern
//			.compile("[a-zA-Z0-9]{5,20}");
//	private static final Pattern CREDITS_PATTERN = Pattern
//			.compile("[0-9]{1,2}");
//	private static final Set<String> FORBINDDDEN_WORD_SET = new HashSet<String>();
//
//	static {
//		FORBINDDDEN_WORD_SET.add("fuck");
//		FORBINDDDEN_WORD_SET.add("admin");
//	}
//
//	@Override
//	public boolean supports(Class<?> clazz) {
//		return User.class == clazz;
//	}
//
//	@Override
//	public void validate(Object target, Errors errors) {
//		ValidationUtils.rejectIfEmpty(errors, "userName", "username.not.empty");
//		User user = (User) target;
//		if (!USERNAME_PATTERN.matcher(user.getUserName()).matches()) {
//			errors.rejectValue("userName", "username.not.illegal");// 如果用户名不合法
//		}
//		for (String forbiddenWord : FORBINDDDEN_WORD_SET) {
//			if (user.getUserName().contains(forbiddenWord)) {
//				errors.rejectValue("userName", "username.forbidden",
//						new Object[] { forbiddenWord }, "您的用户名包含非法关键词");// 用户名包含屏蔽关键字
//				break;
//			}
//		}
//		if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
//			errors.rejectValue("password", "password.not.illegal", "密码不合法");// 密码不合法
//		}
//		if (!CREDITS_PATTERN.matcher(String.valueOf(user.getCredits())).matches()) {
//			errors.rejectValue("credits", "credits.not.illegal", "初始学分不合法");// 初始学分不合法
//		}
//	}
//
//}
