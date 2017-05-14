package com.gajendra.example;
import com.gajendra.example.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;
import java.util.List;

@RepositoryRestController
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @RestResource(path = "/by-name")
    public Collection<Reservation> findByReservationName (@Param("rn") String name);

    @RestResource()
    public List<Reservation> findAll();
}
