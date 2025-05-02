public interface Reserveable {
    int reserve() throws Exception;
    int cancelReservation() throws Exception;
}
