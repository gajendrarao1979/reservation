package com.gajendra.example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;
import java.util.List;

@RepositoryRestController
public interface ReservationRepository extends JpaRepository<MyReservation, Long> {

 /*   @RestResource(path = "byReservationName")
    public Collection<MyReservation> findByReservationName (@Param("rn") String name);

    @RestResource()
    public List<MyReservation> findAll();*/
}
