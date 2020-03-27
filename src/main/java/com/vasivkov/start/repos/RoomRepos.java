package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepos extends JpaRepository<Room, Integer> {
    @Query("Select  n  FROM Room  n Where id = :roomId")
    Optional<Room> findFromRoom(int roomId);

    @Query("Select n.square FROM Room  n Where id = :roomId")
    Optional<Float> findSquare(int roomId);
}

