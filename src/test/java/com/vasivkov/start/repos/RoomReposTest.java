package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RoomReposTest {

    @Autowired
    private RoomRepos roomRepos;

    @Test
    public void test() {
        Room room = Room.builder().square(10.0F).build();
        roomRepos.save(room);
        Optional<Room> result = roomRepos.findFromRoom(1);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(10.0F, result.get().getSquare().floatValue(), 0.01 );
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
        Room room = Room.builder().square(15.0F).build();
        Room save = roomRepos.save(room);
        Optional<Float> result = roomRepos.findSquare(save.getId());
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(15.0F, result.get().floatValue(), 0.01 );

    }

}