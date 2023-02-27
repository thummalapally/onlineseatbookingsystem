package com.capg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capg.entity.Booking;
import com.capg.entity.User;
import com.capg.exception.BookingNotFoundException;
import com.capg.exception.UserNotFoundException;
import com.capg.repository.BookingRepository;
import com.capg.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public String addUser(User user) {
		user.setuId(null);
		this.userRepository.saveAndFlush(user);
		return "User added successfully";
	}

	@Override
	public List<User> listAllUsers() throws UserNotFoundException {
		return userRepository.findAll();
	}

	@Override
	public User loginUser(String emailId, String password) throws UserNotFoundException {
		User bean = new User();
		try {
			for (User i : userRepository.findAll()) {
				if (i.getEmailId().equals(emailId) && i.getPassword().equals(password)) {
					bean = i;
				}
			}
		} catch (Exception e) {
			throw new UserNotFoundException("User details not found!");
		}
		return bean;
	}

//	@Override
//	public String loginUser(String emailId, String password) throws UserNotFoundException {
//		User bean = new User();
//		try {
//			bean = this.findByEmailId(emailId);
//			if(bean.getPassword().equals(password)) {
//				return "Logged in successfully";
//			}
//		}
//		catch(Exception e) {
//			throw new UserNotFoundException("User details not found!");
//		}
//		 return "Logged in successfully";
//	}

	@Override
	public User findByUserEmailId(String emailId) throws UserNotFoundException {
		return this.findByEmailId(emailId);
	}

	@Override
	public boolean isAdmin(String emailId) {
		User bean = new User();
		try {
			bean = this.findByEmailId(emailId);
			if (bean != null && bean.getRole().equals("Admin")) {
				return true;
			}
		} catch (Exception e) {
			throw new UserNotFoundException("User details not found!");
		}
		return false;
	}

//	@Override
//	public boolean validateSecurityAnswer(String emailId, String securityAnswer) {
//		User bean = new User();
//		try {
//			bean = this.findByEmailId(emailId);
//			if (bean != null && bean.getEmailId().equals(emailId) && bean.getSecurityAnswer().equals(securityAnswer)) {
//				return true;
//			}
//		} catch (Exception e) {
//			throw new UserNotFoundException("User details not found!");
//		}
//		return false;
//	}

//	@Override
//	public void resetPassword(String emailId, String password) {
//		try {
//			for (User i : userRepository.findAll()) {
//				if (i.getEmailId().equals(emailId)) {
//					i.setPassword(password);
//				}
//			}
//		} catch (Exception e) {
//			throw new UserNotFoundException("User details not found!");
//		}
//
//	}

	@Override
	public User deleteByUserEmailId(String emailId) throws UserNotFoundException {
		return this.deleteByUserEmailId(emailId);
	}
//	@Override
//	public User deleteUserByEmailId(String emailId, String password) {
//		User bean = new User();
//		try {
//			bean = this.findByEmailId(emailId);
//			userRepository.deleteById(bean.getuId());
//		} catch (Exception e) {
//			throw new UserNotFoundException("User details not found!");
//		}
//		return bean;
//	}

//	@Override
//	public void updateUserPasswordByEmailId(String emailId, String password) throws UserNotFoundException {
//		User bean = new User();
//		try {
//			for (User i : userRepository.findAll()) {
//				if (i.getEmailId().equals(emailId) && i.getPassword().equals(password)) {
//					bean = i;
//				}
//			}
//		} catch (Exception e) {
//			throw new UserNotFoundException("User details not found!");
//		}
//		userRepository.saveAndFlush(bean);
//
//	}

	@Override
	public User findByEmailId(String emailId) {
		User bean = new User();
		try {
			bean = userRepository.findByEmailId(emailId);
		} catch (Exception e) {
			throw new UserNotFoundException("User details not found!");
		}
		return bean;
	}

	@Override
	public void save(User users) {
		userRepository.saveAndFlush(users);
	}

	@Override
	public boolean swapBookings(int bookingId1, int bookingId2) throws BookingNotFoundException {

		Booking bean1 = null;
		Booking bean2 = null;
		try {
			bean1 = bookingRepository.findById(bookingId1).get();
			bean2 = bookingRepository.findById(bookingId2).get();
		} catch (Exception e) {
			throw new BookingNotFoundException("Booking details not found!");
		}
		User temp = bean1.getUser();
		bean1.setUser(bean2.getUser());
		bean2.setUser(temp);
		return true;
	}

}
