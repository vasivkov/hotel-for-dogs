package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    private RoomRepos roomRepos;

    @GetMapping(value = "/room")
    public Optional<Room> main() {

        return roomRepos.findFromRoom(1);
    }


}
