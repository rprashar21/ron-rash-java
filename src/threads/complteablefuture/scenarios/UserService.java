package threads.complteablefuture.scenarios;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class UserService {

    // here we are using the then compose
    //fetchUserIdFromApi() simulates a REST call (e.g., calling another service).
    //
    //fetchUserDetailsFromDb(userId) simulates a DB call.
    //
    //thenCompose() chains them: the DB call only runs after the user ID is received.
    //
    //userFuture is a flat CompletableFuture<User>, so it's easy to handle further (e.g., send as response, log, transform, etc.).

    // Simulated REST API call - async
    public static CompletableFuture<Integer> fetchUserIdFromApi() {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay("API call to fetch userId");
            return 42;
        });
    }

    // Simulated DB call - async
    public static CompletableFuture<User> fetchUserDetailsFromDb(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay("DB query for userId: " + userId);
            return new User(userId, "Swati", "swati@example.com");
        });
    }

    public static void main(String[] args) {
        CompletableFuture<User> userFuture = fetchUserIdFromApi()
                .thenCompose(userId -> fetchUserDetailsFromDb(userId));

        CompletableFuture<User> userAplied = userFuture.thenApply(user -> {
            String userName = user.getName().concat(" Acharya");
            user.setName(userName);
            return user;
        });

        userAplied.thenAccept(user -> {
            System.out.println("User fetched: " + user);
        }).join();

    }

    static void simulateDelay(String msg) {
        System.out.println("▶ " + msg);
        sleep(1);
    }

    static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // POJO
    static class User {
        int id;
        String name;
        String email;

        User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(final int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(final String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return String.format("User{id=%d, name='%s', email='%s'}", id, name, email);
        }
    }
}

