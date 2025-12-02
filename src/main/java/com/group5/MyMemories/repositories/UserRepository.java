package com.group5.MyMemories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.group5.MyMemories.entity.User;
@Repository

public interface UserRepository extends JpaRepository<User, Integer> {

}
