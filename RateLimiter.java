import java.util.concurrent.atomic.AtomicLong;

/**
 * A simple Token Bucket Rate Limiter.
 * Demonstrates thread-safe resource management and timing logic.
 */
public class RateLimiter {
    private final long maxTokens;
    private final long refillRatePerMs;
    private final AtomicLong currentTokens;
    private long lastRefillTimestamp;

    public RateLimiter(long maxTokens, long refillRatePerMs) {
        this.maxTokens = maxTokens;
        this.refillRatePerMs = refillRatePerMs;
        this.currentTokens = new AtomicLong(maxTokens);
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refill();
        if (currentTokens.get() > 0) {
            currentTokens.decrementAndGet();
            return true;
        }
        return false; // Rate limit exceeded
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long duration = now - lastRefillTimestamp;
        long tokensToAdd = duration * refillRatePerMs;

        if (tokensToAdd > 0) {
            long newValue = Math.min(maxTokens, currentTokens.get() + tokensToAdd);
            currentTokens.set(newValue);
            lastRefillTimestamp = now;
        }
    }
}
