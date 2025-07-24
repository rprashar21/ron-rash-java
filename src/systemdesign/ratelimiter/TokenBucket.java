package systemdesign.ratelimiter;

public class TokenBucket {
    private int capacity;          // Max tokens in the bucket
    private double tokens;         // Current token count (can be fractional)
    private double refillRate;     // Tokens per millisecond
    private long lastRefillTime;   // Last time tokens were added

    public TokenBucket(int capacity, int tokensPerMinute) {
        this.capacity = capacity;
        this.tokens = capacity; // Start full
        this.refillRate = (double) tokensPerMinute / 60_000; // tokens per millisecond
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        refillTokens(now);

        if (tokens >= 1) {
            tokens -= 1;
            return true;
        }
        return false;
    }

    private void refillTokens(long now) {
        long elapsed = now - lastRefillTime;
        double tokensToAdd = elapsed * refillRate;

        if (tokensToAdd > 0) {
            tokens = Math.min(capacity, tokens + tokensToAdd);
            lastRefillTime = now;
        }
    }


    //
    public static void main(String[] args) throws InterruptedException {
        TokenBucket userLimiter = new TokenBucket(5, 5); // 5 tokens per minute

        for (int i = 0; i < 10; i++) {
            boolean allowed = userLimiter.allowRequest();
            System.out.println("Request " + i + " allowed? " + allowed);
            Thread.sleep(500); // Simulate a user sending requests every 0.5 seconds
        }
    }



}

