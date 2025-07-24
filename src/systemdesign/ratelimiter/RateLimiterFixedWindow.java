package systemdesign.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterFixedWindow {

    // this is a fixed window rate limiter
    // here the window size is given alos number of requests per user and refill time
    // we will use a in memory map to store the number of requests per user

    private final int LIMIT = 5;
    private final long WINDOW_SIZE = 60_000; // 1 minute in ms
    // this map will store the number of requers per user id and time
    private final Map<String, RequestCounter> userRequestMap = new ConcurrentHashMap<>();


    // method to check if the request is allowed or not -- if the user id is present wwe will get it from the map
    // if not the uerid is new user or ip and we will add the user id to the map
    public boolean isAllowed(String userId) {
        long currentTime = System.currentTimeMillis();
        RequestCounter requestCounter = userRequestMap.getOrDefault(userId, new RequestCounter(0, 0));

        long currentWindow = currentTime / WINDOW_SIZE;

        if (requestCounter.windowStartTime != currentWindow) {
            requestCounter.windowStartTime = currentWindow;
            requestCounter.count = 1;
        } else {
            if (requestCounter.count >= LIMIT) return false;
            requestCounter.count++;
        }

        userRequestMap.put(userId, requestCounter);
        return true;
    }

    public static void main(String[] args) {
        RateLimiterFixedWindow rateLimiterFixedWindow = new RateLimiterFixedWindow();
        for (int i = 0; i < 10; i++) {
            System.out.println(rateLimiterFixedWindow.isAllowed("rohit"));
        }
    }




    class RequestCounter {
        long windowStartTime;
        int count;

        public RequestCounter(final long windowStartTime, final int count) {
            this.windowStartTime = windowStartTime;
            this.count = count;
        }
    }


}
