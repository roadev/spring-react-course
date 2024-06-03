package dev.juanroa.sprintreactnew.data.repositories;

import dev.juanroa.sprintreactnew.data.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

}
