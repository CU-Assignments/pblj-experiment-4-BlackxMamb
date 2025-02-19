import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableSeats;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public void bookSeat(String customerName) {
        lock.lock();
        try {
            if (availableSeats > 0) {
                System.out.println(customerName + " successfully booked a seat. Remaining seats: " + (--availableSeats));
            } else {
                System.out.println(customerName + " attempted to book but no seats are available.");
            }
        } finally {
            lock.unlock();
        }
    }
}

class Customer extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerName;

    public Customer(TicketBookingSystem system, String name, int priority) {
        this.bookingSystem = system;
        this.customerName = name;
        setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(customerName);
    }
}

public class TicketBookingMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5);

        Customer vip1 = new Customer(system, "VIP_John", Thread.MAX_PRIORITY);
        Customer vip2 = new Customer(system, "VIP_Sarah", Thread.MAX_PRIORITY);
        Customer regular1 = new Customer(system, "Regular_Alex", Thread.NORM_PRIORITY);
        Customer regular2 = new Customer(system, "Regular_Emma", Thread.NORM_PRIORITY);
        Customer regular3 = new Customer(system, "Regular_Mike", Thread.NORM_PRIORITY);
        Customer regular4 = new Customer(system, "Regular_Lisa", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();
        regular4.start();
    }
}
