package com.hotel.db.repositories;

import com.hotel.db.entities.Booking;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Page<Booking> findAll(Pageable pageable);

    Page<Booking> findAllByUser(User user, Pageable pageable);

    List<Booking> findAllByRoomAndStartDateGreaterThan(Room room, Date now);

}
