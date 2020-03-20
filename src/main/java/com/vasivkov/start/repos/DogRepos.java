package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Dog;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.Set;

import java.util.Optional;

public interface DogRepos extends CrudRepository<Dog, Integer> {
    Set<Dog> findByOwnerId(int ownerId);
    Optional<Dog> findByIdAndOwnerId(int id, int instructorId);
}
