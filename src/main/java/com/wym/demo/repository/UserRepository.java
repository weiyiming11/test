package com.wym.demo.repository;


import com.wym.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "select * from user where username=?1", nativeQuery = true)
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "update user u set u.password =?1,u.email =?2,u.birthday=?3 where u.id=?4",nativeQuery = true)
    void updateUser(String password,String email,String birthday,long id);

}
