package exploringrailwayprogramming;

import java.time.Instant;
import java.util.List;

public interface ReservationRepository {
    List<Reservation> readReservations(Instant date);
    void register(Reservation reservation);
}
