package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Reserve;
import com.vasivkov.start.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ReserveRepos extends JpaRepository<Reserve, Integer> {

    @Query("SELECT r FROM Room r LEFT JOIN r.reserves re WHERE r.id = :id AND re.dateStart > :to ")
    Optional<Room> findFreeRooms(Integer id, Date to);

}


//@Query("SELECT r FROM Room r LEFT JOIN r.reserves re WHERE r.id = :id AND re.dateStart > :to AND re.dateFinish < :from