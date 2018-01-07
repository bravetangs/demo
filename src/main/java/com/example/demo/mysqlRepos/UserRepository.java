package com.example.demo.mysqlRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
//	@Query("select t from User t where t.id=?1 and t.did=?2")
//	public User findByIdAndDid(int id,int did);
//	
//	@Query("select t from User t")
//	public List<User> findBy();
}
