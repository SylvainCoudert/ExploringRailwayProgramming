package exploringrailwayprogramming;

import java.time.Instant;
import java.util.List;

public class MaitreD {
    private final static int MAX_CAPACITY = 10;
    private final ReservationRepository reservationRepository;

    public MaitreD(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void registerReservation(RegisterReservationCommand command) {
        Reservation reservation = createReservation(command.at(), command.email(), command.quantity(), command.name());

        List<Reservation> reservations =  reservationRepository.readReservations(command.at());
        ensureEnoughSeatsAvailable(reservations, reservation);

        reservationRepository.register(reservation);
    }

    private void ensureEnoughSeatsAvailable(List<Reservation> reservations, Reservation reservation) {
        int reservedSeats = reservations.stream().map(Reservation::quantity).mapToInt(Integer::intValue).sum();
        if (MAX_CAPACITY < reservedSeats + reservation.quantity())
            throw new UnsupportedOperationException("Not enough seats available");
    }

    private Reservation createReservation(Instant at, String email, int quantity, String name) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email value");
        }
        if (quantity < 1)
            throw new IllegalArgumentException("Invalid quantity value");

        return new Reservation(at, email, quantity, name == null ? "" : name);
    }
}
