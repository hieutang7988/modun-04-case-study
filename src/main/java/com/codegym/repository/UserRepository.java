package com.codegym.repository;

import com.codegym.model.User;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query(nativeQuery = true, value = "select * " +
            "from users u " +
            "where u.fullname like (:fullname);")
    List<User> findByFullName(@Param("fullname") String fullname);

}
