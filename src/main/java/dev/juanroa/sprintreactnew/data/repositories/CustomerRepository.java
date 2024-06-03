package dev.juanroa.sprintreactnew.data.repositories;

import dev.juanroa.sprintreactnew.data.entities.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

  CustomerEntity findByEmail(String email);

}
