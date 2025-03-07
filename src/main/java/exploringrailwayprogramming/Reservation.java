package exploringrailwayprogramming;

import java.time.Instant;

public record Reservation(Instant at, String email, int quantity, String name) {
}
