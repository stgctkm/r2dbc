package com.example.r2dbc.infrastructure;

import com.example.r2dbc.domain.Reservation;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ReservationRepository {

    ConnectionFactory connectionFactory;

    Mono<Void> deleteById(Integer id) {
        Flux<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("delete from  reservation where id = $1")
                        .bind("$1", id)
                        .add()
                        .execute()
                );
        return null;
    }

    Flux<Reservation> findAll() {
        return this.connection()
                .flatMapMany(connection ->
                    Flux.from(connection.createStatement("select * from reservation").execute())
                            .flatMap((Result result) -> result.map((row, rowMetaData) ->
                                    new Reservation(
                                            row.get("id", Integer.class), row.get("name", String.class))))
                );
    }

    Flux<Reservation> save(Reservation reservation) {
        Flux<? extends Result> flatMapMany = this.connection()
                .flatMapMany(conn -> conn.createStatement("insert into reservation (name) values ($1)")
                        .bind("$1", reservation.getName())
                        .add()
                        .execute()
                );
        return flatMapMany
                .switchMap(x -> Flux.just(new Reservation(reservation.getId(), reservation.getName())));
    }

    private Mono<Connection> connection() {
        return Mono.from(connectionFactory.create());
    }

    ReservationRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}
