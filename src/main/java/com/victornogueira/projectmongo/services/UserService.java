package com.victornogueira.projectmongo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.victornogueira.projectmongo.domain.User;
import com.victornogueira.projectmongo.dto.UserDto;
import com.victornogueira.projectmongo.respositories.UserRepository;
import com.victornogueira.projectmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		List<User> listUsers = userRepository.findAll();
		return listUsers;
	}

	public User findById(String id) {
		return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public User insertUser(User user) {
		return userRepository.insert(user);
	}

	public void deleteUser(String id) {
		User user = findById(id);
		userRepository.delete(user);
	}
	
	public User update(String id, UserDto userDto) {

		User user = findById(id);
		//updateData(userData, user);
		user.updateName(userDto.getName());
		user.updateEmail(userDto.getEmail());
		
		return userRepository.save(user);
	}

	
// Esse método seria para atualização dos dados, mas como no momento a lógica é simples, optei por deixar no update()
	
//	private static void updateData(User userData, User user) {
//		
//		userData.updateName(user.getName());
//		userData.updateEmail(user.getEmail());
//	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}