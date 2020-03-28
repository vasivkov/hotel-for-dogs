package com.vasivkov.start.repos;

import com.vasivkov.start.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomRepos extends JpaRepository<Room, Integer> {
    @Query("Select  n  FROM Room  n Where id = :roomId")
    Optional<Room> findFromRoom(int roomId);

    @Query("Select n.square FROM Room  n Where id = :roomId")
    Optional<Float> findSquare(int roomId);

    @Query("SELECT r FROM Room r WHERE square > :roomSquare")
    List<Room> findRoomsBySquare (Float roomSquare);

    @Modifying
    @Query(value = "INSERT INTO rooms (square, created_at) VALUES (:roomSquare, :roomCreatedAt)", nativeQuery = true)
    @Transactional
    void insertInRoom(@Param("roomSquare") Float square, @Param("roomCreatedAt")Date date);


}

