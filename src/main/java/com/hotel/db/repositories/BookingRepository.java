package com.hotel.db.repositories;

import com.hotel.db.entities.Booking;
import com.hotel.db.entities.Room;
import com.hotel.db.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    Page<Booking> findAll(Pageable pageable);

    Set<Booking> findAllByUser(User user);

    List<Booking> findAllByRoomAndStartDateGreaterThan(Room room, Date now);

}
