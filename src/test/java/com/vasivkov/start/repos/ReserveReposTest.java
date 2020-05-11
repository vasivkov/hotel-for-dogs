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
    private Reserve testReserve;

    @Before
    public void before() {
        final Date DATE_START = new GregorianCalendar(2020, Calendar.APRIL, 01).getTime();
        final Date DATE_FINISH = new GregorianCalendar(2020, Calendar.APRIL, 30).getTime();

        Room room = Room.builder().square(20F).createdAt(new Date()).reserves(new ArrayList<>()).build();
        Owner owner = Owner.builder().name("John").build();

        testRoom = roomRepos.save(room);
        Owner savedOwner = ownerRepos.save(owner);

        Dog dog = Dog.builder().name("Jack").owner(savedOwner).build();
        Dog savedDog = dogRepos.save(dog);

        Reserve reserve = Reserve.builder().room(testRoom).dog(savedDog).dateStart(DATE_START).dateFinish(DATE_FINISH).build();
        testReserve = reserveRepos.save(reserve);

    }

    @Test
    public void roomIsEmpty() {
        Optional<Room> freeRooms = reserveRepos.findFreeRoom(testRoom.getId(),
                new GregorianCalendar(2020, Calendar.APRIL, 29).getTime(),
                new GregorianCalendar(2020, Calendar.MAY, 15).getTime());
        Assert.assertFalse(freeRooms.isPresent());
    }



    @Test
    public void roomIsFree() {
        Optional<Room> freeRooms = reserveRepos.findFreeRoom(testRoom.getId(),
                new GregorianCalendar(2020, Calendar.MAY, 01).getTime(),
                new GregorianCalendar(2020, Calendar.MAY, 30).getTime());
        Assert.assertFalse(freeRooms.isPresent());
    }

    @Test
    public void discoverEmptyRoom() {
        Room room = new Room();
        Room tmpRoom = roomRepos.save(room);
        Optional<Room> testRoom = reserveRepos.findFreeRoom(tmpRoom.getId(), new Date(), new Date());
        Assert.assertFalse(testRoom.isPresent());
    }

    @Test
    public void innerInterval(){
        Optional<Room> freeRooms = reserveRepos.findFreeRoom(testRoom.getId(),
                new GregorianCalendar(2020, Calendar.APRIL, 02).getTime(),
                new GregorianCalendar(2020, Calendar.APRIL, 30).getTime());
        Assert.assertFalse(freeRooms.isPresent());
    }



}
