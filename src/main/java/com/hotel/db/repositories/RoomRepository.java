package com.hotel.db.repositories;

import com.hotel.db.entities.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findAll();

}
