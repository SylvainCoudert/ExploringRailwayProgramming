package exploringrailwayprogramming;

import java.time.Instant;
import java.util.List;

public class SpyRepository implements ReservationRepository {

    private final List<Reservation> _reservations;
    private Reservation _reservation = null;

    public SpyRepository(List<Reservation> reservations) {
        _reservations = reservations;
    }
    public SpyRepository() {
        _reservations = List.of();
    }

    public Reservation getCreatedReservation() {
        return _reservation;
    }

    @Override
    public List<Reservation> readReservations(Instant date) {
        return _reservations.stream().toList();
    }

    @Override
    public void register(Reservation reservation) {
        _reservation = reservation;
    }
}
