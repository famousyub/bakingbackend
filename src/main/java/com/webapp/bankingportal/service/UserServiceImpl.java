package com.webapp.bankingportal.service;

import com.webapp.bankingportal.exception.UserValidation;
import com.webapp.bankingportal.util.LoggedinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.entity.User;
import com.webapp.bankingportal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AccountService myaccountService;


    public UserServiceImpl(UserRepository userRepository, AccountService accountService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder =  passwordEncoder;
    }
    
    @Override
    public User getUserByAccountNumber(String account_no) {
    	return userRepository.findByAccountAccountNumber(account_no);
    }
    
    @Override
    public User registerUser(User user) {
        
    	 String encodedPassword = passwordEncoder.encode(user.getPassword());
         user.setPassword(encodedPassword);

        // Save the user details
        User savedUser = userRepository.save(user);

        // Create an account for the user
        Account account = accountService.createAccount(savedUser);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        savedUser.setAccount(accounts);
        userRepository.save(savedUser);
        
        System.out.println(savedUser.getAccount().get(0));
        System.out.println(account.getUser().getName());

        
        return savedUser;
    }

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findByAccountAccountNumber(LoggedinUser.getAccountNumber());
        if(user.getEmail() != null){
            if(user.getEmail().isEmpty())
                throw new UserValidation("Email can't be empty");
            else
                existingUser.setEmail(user.getEmail());
        }
        if(user.getName() != null){
            if(user.getName().isEmpty())
                throw new UserValidation("Name can't be empty");
            else
                existingUser.setName(user.getName());
        }
        if(user.getPhone_number() != null){
            if(user.getPhone_number().isEmpty())
                throw new UserValidation("Phone number can't be empty");
            else
                existingUser.setPhone_number(user.getPhone_number());
        }
        if(user.getAddress() != null){
            existingUser.setAddress(user.getAddress());
        }
        return userRepository.save(existingUser);
    }












    public User findByUsername(String username) {
        return userDao.findByUsername(username).get();
    }
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }



    public boolean checkUserExists(String username, String email) {
        if (checkUsernameExists(username) || checkEmailExists(email)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }
        return false;
    }
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }
        return false;
    }




    public List<User> findUserList() {
        return userDao.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }


}
