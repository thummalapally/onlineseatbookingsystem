package com.capg.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	User findByEmailId(String emailId);
	

}
