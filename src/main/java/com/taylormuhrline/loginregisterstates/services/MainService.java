package com.taylormuhrline.loginregisterstates.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taylormuhrline.loginregisterstates.models.State;
import com.taylormuhrline.loginregisterstates.models.User;
import com.taylormuhrline.loginregisterstates.repositories.StateRepository;
import com.taylormuhrline.loginregisterstates.repositories.UserRepository;

@Service
public class MainService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	// GET ALL
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public List<State> getAllStates(){
		return stateRepository.findAll();
	}
    
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
    
 // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

	// GET BY ID
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    public State findStateById(Long id) {
    	return stateRepository.findById(id).orElse(null);
    }
    
    // CREATE
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public State createState(State state) {
    	return stateRepository.save(state);
    }
    
    public State createState(String name) {
    	State state = new State(name);
    	return stateRepository.save(state);
    }
}
