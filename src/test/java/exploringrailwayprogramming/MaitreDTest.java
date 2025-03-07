package exploringrailwayprogramming;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MaitreDTest {
    @Test
    public void registerReservationWithoutName() {
        var spyRepository = new SpyRepository();
        var sut = new MaitreD(spyRepository);
        var now = Instant.now();
        var command = new RegisterReservationCommand(now, "mark@example.com", 5, null);
        sut.registerReservation(command);

        var expectedReservation = new Reservation(now, "mark@example.com", 5, "");
        assertThat(spyRepository.getCreatedReservation()).isEqualTo(expectedReservation);
    }

    @Test
    public void registerReservationWithName() {
        var spyRepository = new SpyRepository();
        var sut = new MaitreD(spyRepository);
        var now = Instant.now();

        var command = new RegisterReservationCommand(now, "mark@example.com", 5, "Mark");
        sut.registerReservation(command);

        var expectedReservation = new Reservation(now, "mark@example.com", 5, "Mark");
        assertThat(spyRepository.getCreatedReservation()).isEqualTo(expectedReservation);
    }

    @Test
    public void failWhenNotEnoughSeats() {
        var now = Instant.now();
        var spyRepository = new SpyRepository(List.of(
                new Reservation(now, "bob@example.com", 2, "Bob"),
                new Reservation(now, "alice@example.com", 6, "Alice")));
        var sut = new MaitreD(spyRepository);

        var command = new RegisterReservationCommand(now, "mark@example.com", 4, "Mark");

        assertThatThrownBy(() -> sut.registerReservation(command))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not enough seats available");
    }

    @Test
    public void failWhenEmailIsMissing() {
        var spyRepository = new SpyRepository();
        var sut = new MaitreD(spyRepository);
        var now = Instant.now();

        var command = new RegisterReservationCommand(now, "", 4, "Mark");

        assertThatThrownBy(() -> sut.registerReservation(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email value");
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    public void failWhenQuantityIsLowerThan1(int quantity) {
        var spyRepository = new SpyRepository();
        var sut = new MaitreD(spyRepository);
        var now = Instant.now();

        var command = new RegisterReservationCommand(now, "mark@example.com", quantity, "Mark");

        assertThatThrownBy(() -> sut.registerReservation(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid quantity value");
    }
}
