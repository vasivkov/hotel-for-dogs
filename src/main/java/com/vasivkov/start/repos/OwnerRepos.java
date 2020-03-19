package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepos extends CrudRepository<Owner, Integer> {

}
