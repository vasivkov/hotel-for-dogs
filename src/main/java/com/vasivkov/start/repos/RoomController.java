package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {

    @Autowired
    private RoomRepos roomRepos;

    @Autowired
    private ReserveRepos reserveRepos;

    @GetMapping("get/rooms/{dateFrom}/{dateTo}")
    public List<Room> getFreeRooms (@PathVariable Date dateFrom, @PathVariable Date dateTo){
        List<Room> existingRooms = roomRepos.findAll();
        List <Room> freeRooms = new ArrayList<>();
        for (Room room: existingRooms
             ) {
            Optional <Room> optionalRoom = reserveRepos.findFreeRoom(room.getId(), dateFrom, dateTo);
            if(optionalRoom.isPresent()){
                freeRooms.add(optionalRoom.get());
            }
        }
        return  freeRooms;
    }

}

