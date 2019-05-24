package com.example.r2dbc.infrastructure;

import com.example.r2dbc.domain.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReactiveReservationRepositoryTest {

    ReactiveReservationRepository repository;

    @Test
    public void findByName() {

        Flux<Reservation> reservations = Flux.just("first", "secound", "third")
                .flatMap(name -> repository.save(new Reservation(null, name)));

        Flux<Reservation> first = repository.findByName("first");
        StepVerifier
                .create(first)
                .expectNextCount(1)
                .verifyComplete();

    }
}