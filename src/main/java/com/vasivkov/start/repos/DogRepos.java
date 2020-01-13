package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepos extends CrudRepository<Dog, Integer> {
}
