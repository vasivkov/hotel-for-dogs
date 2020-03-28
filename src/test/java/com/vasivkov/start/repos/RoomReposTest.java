package com.vasivkov.start.repos;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.vasivkov.start.domain.Room;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RoomReposTest {

    @Autowired
    private RoomRepos roomRepos;

    @Before
    public void fillWithData() {
        roomRepos.insertInRoom(20F, new Date());
        roomRepos.insertInRoom(13F, new Date());
        roomRepos.insertInRoom(15F, new Date());

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
        Assert.assertEquals(22.0F, result.get().floatValue(), 0.01);
    }

    @Test
    public void test4() {
        Assert.assertEquals(3, roomRepos.count());
    }

    @Test
    @Ignore
    public void test5(){
        Assert.assertEquals(15F, roomRepos.findSquare(3).get(),0.01);

    }

    @Test
    public void findBySquareTest(){
        Assert.assertEquals(2, roomRepos.findRoomsBySquare(14F).size());
        Assert.assertEquals(0, roomRepos.findRoomsBySquare(25F).size());

    }

    @Ignore
    public void emptyTable() {
        roomRepos.deleteAll();
    }

}