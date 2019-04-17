package com.hotel.db.repositories;

import com.hotel.db.entities.CategoryRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRoomRepository extends CrudRepository<CategoryRoom, Long> {

    List<CategoryRoom> findAll();
}
