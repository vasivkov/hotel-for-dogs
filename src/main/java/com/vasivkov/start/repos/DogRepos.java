package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Dog;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DogRepos extends CrudRepository<Dog, Integer> {
    Optional<Dog> findByNameAndWeight (String name, int weight);
}
