package systemdesign.ratelimiter;

public class TokenBucketDemo {
    public static void main(String[] args) throws InterruptedException {
//        TokenBucket userLimiter = new TokenBucket(5, 5); // 5 tokens per minute
//
//        for (int i = 0; i < 30; i++) {
//            boolean allowed = userLimiter.isAllowed();
//            System.out.println("Request " + i + " allowed? " + allowed);
//            Thread.sleep(500); // Simulate a user sending requests every 0.5 seconds
//        }
    }
}


//class TokenBucket {
//    // a bucket will have a fixed number of tokens ie the maximum number of requests that can be made
//    // we will have a refillrate -- ie how many tokens will be added to the bucket after a certain period
//    // we wil have a currentTokenCount ie the current number of tokens in the bucket this will be decremented after each request
//    // we will have the lastrefillTime -- the last time the bucket was refilled
//
//    private int maxTokens;
//    private double refillRate; // lets say 5 tokes are added in a min ie 1 token is added every 12 seconds ie 1/5th of a minute or 60seconds
//    private int currentTokenCount;
//    private long lastRefillTime;
//
//
//    // why a constructor -- we need to initialize the values you can decide the maxTokens and refillRate
//    public TokenBucket(int maxTokens, int tokensPerMinute) {
//        this.maxTokens = maxTokens;
//        this.refillRate = (double)tokensPerMinute/60_000; // 1/5th of a minute
//        this.currentTokenCount = maxTokens;
//        this.lastRefillTime = System.currentTimeMillis();
//    }
//
//    // method to check if the request is allowed or not
//
//    public boolean isAllowed() {
//
//        long currentTime = System.currentTimeMillis(); // give me the current time in millis
//        refillTokensNow(currentTime); // i will call the method to check if the bucket needs to be refilled
//        if(currentTokenCount > 0) {
//            currentTokenCount--;
//            return true;
//        }
//        return false;
//    }
//
//    private void refillTokensNow(final long currentTime) {
//        long timeElapsed = currentTime - lastRefillTime;
//        double tokensToAdd = (double)timeElapsed *refillRate;
//
//
//        // makes sure that the currentTokenCount does not exceed the maxTokens
//        currentTokenCount = (int) Math.min(currentTokenCount + tokensToAdd, maxTokens);
//        lastRefillTime = currentTime;
//    }
//
//}