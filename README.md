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


## Day 2: Advanced Data Structures - Trie (Prefix Tree)
- **Concept:** A Prefix Tree to optimize search-ahead functionality.
- **Focus:** Practiced recursive logic and memory-efficient node mapping.
- **Application:** Used in low-latency systems like autocomplete engines and IP routing tables.

## Day 3: Secure Token Registry (Concurrent Trie)
- **Concept:** A high-concurrency Trie for sensitive token prefix validation. 
- **Focus:** Optimized for **Enterprise Banking API protocols (SAML/OAuth)** by ensuring thread-safe lookups without global locks.
- **Concurrency:** Utilized `ConcurrentHashMap` for non-blocking child node allocation.
- **Application:** Logic to validate token types before authorization.

## Day 4: Reactive Event Bridge (Spring WebFlux & Kafka)
- **Concept:** A fully non-blocking data pipeline to ingest high-volume event streams.
- **Focus:** Optimized for Reactive Systems by bridging the gap between HTTP entry points (WebFlux) and distributed message brokers (Kafka).
- **Concurrency:** Utilized flatMap and Mono.fromFuture to handle hand-offs without thread blocking, ensuring high throughput under load.
- **Application:** Real-time ingestion of telemetry or audit logs into an event-driven architecture.


