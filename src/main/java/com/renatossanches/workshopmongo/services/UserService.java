package com.renatossanches.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatossanches.workshopmongo.domain.User;
import com.renatossanches.workshopmongo.dto.UserDTO;
import com.renatossanches.workshopmongo.repository.UserRepository;
import com.renatossanches.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
	    Optional<User> userOpt = repo.findById(id);
	    return userOpt.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
	    Optional<User> optionalNewObj = repo.findById(obj.getId());
	    
	    if (optionalNewObj.isEmpty()) {
	        throw new ObjectNotFoundException("User not found with id: " + obj.getId());
	    }
	    
	    User newObj = optionalNewObj.get();
	    updateData(newObj, obj);
	    
	    return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
}
