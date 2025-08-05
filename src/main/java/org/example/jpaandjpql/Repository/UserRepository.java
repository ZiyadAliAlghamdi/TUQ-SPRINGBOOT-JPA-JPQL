package org.example.jpaandjpql.Repository;

import org.example.jpaandjpql.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.id=?1")
    User getUserById(Integer id);


    @Query("SELECT u from User u")
    List<User> getAllUsers();


    @Query("SELECT u from User u where u.mail=:mail")
    User getUserByMail(String mail);


    @Query("SELECT u from  User u where u.role =:role")
    List<User> getUsersByRole(String role);


    @Query("SELECT u from User u where u.age>=?1")
    List<User> getUsersByAge(Integer range);


    @Query("SELECT u from User u where u.mail=:mail and u.password=:password")
    User CheckMailAndPassword(String mail, String password);


}
