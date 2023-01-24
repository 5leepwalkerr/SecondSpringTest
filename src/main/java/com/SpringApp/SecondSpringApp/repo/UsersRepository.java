package com.SpringApp.SecondSpringApp.repo;

import com.SpringApp.SecondSpringApp.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
}
