package com.example.r2dbc.infrastructure;

import com.example.r2dbc.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;


    @Test
    public void deleteById() {
        Flux<Object> deleteAll = reservationRepository.findAll().flatMap(r -> reservationRepository.deleteById(r.getId()));

        StepVerifier.create(deleteAll)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        Flux<Reservation> all = reservationRepository.findAll();
        StepVerifier.create(all)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void save() {
        Flux<Reservation> reservationFlux = Flux.just("first", "second", "thrid")
                .map(name -> new Reservation(null, name));
        Flux<Reservation> reservationFlux1 = reservationFlux.flatMap(r -> reservationRepository.save(r));

        StepVerifier.create(reservationFlux1)
                .expectNextCount(3)
                .verifyComplete();
    }
}