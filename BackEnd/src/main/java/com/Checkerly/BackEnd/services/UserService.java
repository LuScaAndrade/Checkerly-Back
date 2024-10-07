package com.Checkerly.BackEnd.services;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Checkerly.BackEnd.domain.User;
import com.Checkerly.BackEnd.dto.UserDTO;
import com.Checkerly.BackEnd.repository.UserRepository;
import com.Checkerly.BackEnd.services.exception.ObjectNotFoundException;
 
@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
	    Optional<User> user = repo.findById(id);
	    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
        String encodedPassword = passwordEncoder.encode(obj.getSenha());
        obj.setSenha(encodedPassword);
        return repo.save(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(),objDto.getSenha(), objDto.getEmail(), objDto.getCelular());
	}
}
