package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Dog;
import com.vasivkov.start.domain.Owner;
import com.vasivkov.start.domain.Reserve;
import com.vasivkov.start.domain.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ReserveReposTest {

    @Autowired
    private ReserveRepos reserveRepos;

    @Autowired
    private RoomRepos roomRepos;

    @Autowired
    private OwnerRepos ownerRepos;

    @Autowired
    private DogRepos dogRepos;

    private Room testRoom;

    @Before
    public void before(){
        Room room = Room.builder().square(20F).createdAt(new Date()).reserves(new ArrayList<>()).build();
        Owner owner = Owner.builder().name("John").build();

        testRoom = roomRepos.save(room);
        Owner savedOwner = ownerRepos.save(owner);

        Dog dog = Dog.builder().name("Jack").owner(savedOwner).build();
        Dog savedDog = dogRepos.save(dog);

    }

    @Test
    public void roomIsFree(){
        Optional<Room> freeRooms = reserveRepos.findFreeRooms(testRoom.getId(), new Date());
        List<Room> all = roomRepos.findAll();
        System.out.println("____________________" + all + "_________________________________");
        Assert.assertTrue(all.isEmpty());

    }


}
