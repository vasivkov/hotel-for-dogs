package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Dog;
import com.vasivkov.start.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class OwnerController {
@Autowired
   private OwnerRepos ownerRepos;
    @GetMapping(value = "/owners")
    public Iterable<Owner> main() {

        return ownerRepos.findAll();
    }

    @PostMapping(value = "/owner")
    public Owner add(@RequestParam String name, @RequestParam String lastName) {
        Owner owner = new Owner(name, lastName);
        ownerRepos.save(owner);
        Iterable<Owner> owners = ownerRepos.findAll();
        return owner;
    }

}
