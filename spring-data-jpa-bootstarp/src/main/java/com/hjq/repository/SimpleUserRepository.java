package com.hjq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hjq.entity.User;
/**
 * a simple jpa example
 * @author hoh666
 *
 */
@Repository("simpleUserRepository")
public interface SimpleUserRepository extends JpaRepository<User, Long> {

	@Query("select count(*) from User u where LOWER(u.userName) like %:userName%")
	public long getUserCountByUserName(@Param("userName") String userName);

	
}
