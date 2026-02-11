# my-journals
My daily journals, a journey of sharing daily technical experience

#1
## Token Bucket Rate Limiter : Overview
A thread-safe implementation of the **Token Bucket Algorithm**. This is a foundational 
component for building resilient microservices that protect downstream 
resources from traffic spikes.

## Key Concepts
**Concurrency:** Uses `AtomicLong` and `synchronized` blocks to ensure thread safety 
  in high-throughput environments.
* **Efficiency:** $O(1)$ time complexity for request validation.
* **Use Case:** API Throttling, protecting KYC validation endpoints, or preventing 
  brute-force attempts on authentication services.

## Performance
Designed for low-latency overhead, adding sub-millisecond delay to the request pipeline.
