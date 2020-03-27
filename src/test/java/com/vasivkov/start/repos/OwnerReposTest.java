package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class OwnerReposTest {

    @Autowired
    private OwnerRepos ownerRepos;

    @Test
    public void test() {
        ownerRepos.save(new Owner("foo", "bar"));

        List<Owner> all = ownerRepos.findAll();
        all.forEach(o -> {
            System.out.println(o.getName() + " " + o.getLast_name());
        });
    }

}