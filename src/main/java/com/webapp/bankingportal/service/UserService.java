package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.User;

import java.util.List;

public interface UserService {
	public User registerUser(User user);

	User getUserByAccountNumber(String account_no);

	public void saveUser(User user);

	User updateUser(User user);








	User findByUsername(String username);
	User findByEmail(String email);

	boolean checkUserExists(String username, String email);

	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);







	List<User> findUserList();

	void enableUser(String username);

	void disableUser(String username);
}
