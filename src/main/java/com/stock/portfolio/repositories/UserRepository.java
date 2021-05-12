package com.stock.portfolio.repositories;

import com.stock.portfolio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.active = 1")
    List<User> findAllActive();

    @Query("SELECT u FROM User u WHERE u.active = 0")
    List<User> findAllInactive();

    @Query("SELECT u FROM User u WHERE u.roles LIKE '%ADMIN%'")
    List<User> findAllAdmins();

    @Query("SELECT u FROM User u WHERE u.roles LIKE '%USER%'")
    List<User> findAllUsers();

    @Query("SELECT u.id FROM User u")
    List<Long> findAllUserIds();

    @Query("SELECT u.email FROM User u")
    List<String> findAllUserEmails();
}
