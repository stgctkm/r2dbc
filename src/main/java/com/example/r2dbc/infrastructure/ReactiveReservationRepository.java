package com.example.r2dbc.infrastructure;

import com.example.r2dbc.domain.Reservation;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReactiveReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {

    @Query("select * from reservation where name = $1")
    Flux<Reservation> findByName(String name) ;

}
