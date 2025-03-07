package exploringrailwayprogramming;

import java.time.Instant;

public record RegisterReservationCommand(Instant at, String email, int quantity, String name) {
}
