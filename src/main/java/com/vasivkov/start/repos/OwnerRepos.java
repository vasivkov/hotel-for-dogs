package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepos extends JpaRepository<Owner, Integer> {

}
