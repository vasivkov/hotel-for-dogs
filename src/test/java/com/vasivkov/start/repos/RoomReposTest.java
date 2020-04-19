package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RoomReposTest {

    @Autowired
    private RoomRepos roomRepos;

    @Before
    public void fillWithData() {
        Room room = Room.builder().square(20F).createdAt(new Date()).build();
        Room room2 = Room.builder().square(13F).createdAt(new Date()).build();
        Room room3 = Room.builder().square(25F).createdAt(new Date()).build();
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);
        rooms.add(room3);
        roomRepos.saveAll(rooms);
    }

    @Test
    public void test() {
        Room room = Room.builder().square(10.0F).build();
        roomRepos.save(room);
        Optional<Room> result = roomRepos.findFromRoom(4);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(10.0F, result.get().getSquare(), 0.01);
    }

    @Test
    public void test2() {
        Room room = Room.builder().square(15.0F).build();
        roomRepos.save(room);
        Optional<Room> result = roomRepos.findFromRoom(100);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void test3() {
        Room room = Room.builder().square(22.0F).build();
        Room save = roomRepos.save(room);
        Optional<Float> result = roomRepos.findSquare(save.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(22.0F, result.get(), 0.01);
    }

    @Test
    public void test4() {
        Assert.assertEquals(3, roomRepos.count());
    }

    @Test
    public void findBySquareTest() {
        Assert.assertEquals(2, roomRepos.findRoomsBySquare(14F).size());
        Assert.assertEquals(0, roomRepos.findRoomsBySquare(25F).size());

    }

    @After
    public void emptyTable() {
        roomRepos.deleteAll();
    }

}