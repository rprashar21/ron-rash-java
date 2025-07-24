package systemdesign.ratelimiter.realworldexample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import systemdesign.ratelimiter.TokenBucket;

public class TokenRatelimiter {

    ///  interfaces and classes for the real world example
    // let say we have these components

    //ClinetBuilder -- the client actually making a call
    //ClientIdentifierBuilder - extract the user id from the request like an API key or user ID example Example: "alice", "bob", or "client_123".

    //TokenBucketRateLimiter -- this is the main logic
    // uses the ClientIdentifierBuilder to extract the user id
    // uses the TokenBucketCache to check if the user id is allowed to make a request
    // calls the allowRequest() method to decide whether to allow or reject


    // TokenBuckteCche -- stores each users token bucket -- we can use a internal cincurrenthashmap or redis for distriubuted cache
    //5. Rules Service + RetrieveRulesTask + RetrieveJobScheduler
    //Periodically (like every few minutes), fetches rate limit rules from a central service (e.g., “free users = 5 RPM”, “pro users = 20 RPM”).
    //
    //Creates or updates token buckets in TokenBucketCache.

    // example flow
//    Say "alice" sends a request:
//    ClientIdentifierBuilder gets user ID = "alice".
//
//            TokenBucketRateLimiter checks TokenBucketCache for "alice".
//
//    If missing, it might trigger a rule fetch or create a new bucket.
//
//            Calls allowRequest() on alice’s bucket.
//
//    If allowed → proceeds. Else → 429 Too Many Requests
}

//class ClientIdentifierBuilder {
//    //extract the user id from the request like an API key or user ID example Example: "alice", "bob", or "client_123".
//
//    public String getClientId(Request request) {
//        // // In real-world: extract from headers or tokens
//        return request.getUserId();
//    }
//}
//
//class Request {
//    private String userId;
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(final String userId) {
//        this.userId = userId;
//    }
//}
//
//class TokenBucketRateLimiter {
//    private final TokenBucketCache tokenBucketCache;
//    private final ClientIdentifierBuilder clientIdentifierBuilder;
//
//    public TokenBucketRateLimiter(TokenBucketCache cache, ClientIdentifierBuilder builder) {
//        this.tokenBucketCache = cache;
//        this.clientIdentifierBuilder = builder;
//    }
//
//    public boolean isAllowed(Request request) {
//        String clientId = clientIdentifierBuilder.getClientId(request);
//        TokenBucket bucket = tokenBucketCache.getBucket(clientId);
//        return bucket.allowRequest();
//    }
//}
//
//
//class TokenBucketCache {
//    private final ConcurrentHashMap<String, TokenBucket> bucketMap = new ConcurrentHashMap<>();
//
//    public TokenBucket getBucket(String clientId) {
//
//        return bucketMap.computeIfAbsent(clientId,
//                id -> RuleManager.getRulesAndCreateBucket(id));
//    }
//
//    public void putBucket(String clientId, TokenBucket bucket) {
//        bucketMap.put(clientId, bucket);
//    }
//}
//
//
//class RetrieveJobScheduler {
//    public void start(TokenBucketCache cache) {
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(new RetrieveRulesTask(cache), 0, 5, TimeUnit.MINUTES);
//    }
//}
//
//
//public class RetrieveRulesTask implements Runnable {
//    private final TokenBucketCache cache;
//
//    public RetrieveRulesTask(TokenBucketCache cache) {
//        this.cache = cache;
//    }
//
//    @Override
//    public void run() {
//        Map<String, Rule> userRules = RulesService.fetchRules();
//        for (Map.Entry<String, Rule> entry : userRules.entrySet()) {
//            TokenBucket bucket = new TokenBucket(entry.getValue().capacity, entry.getValue().ratePerMinute);
//            cache.putBucket(entry.getKey(), bucket);
//        }
//    }
//}
//
//class Rule {
//
//}
//
//class RulesService {
//    public static Map<String, Rule> fetchRules() {
//        return null;
//    }
//}
//
//class RuleManager {
//    public static TokenBucket getRulesAndCreateBucket(String userId) {
//        return null;
//    }
//}
//
//
//
//
