@RestController
@RequestMapping("/v1/events")
public class EventStreamController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventStreamController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "/stream", consumes = MediaType.APPLICATION_NDJSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> handleEventStream(@RequestBody Flux<String> eventStream) {
        return eventStream
            .flatMap(event -> Mono.fromFuture(kafkaTemplate.send("internal-growth-events", event)))
            .then(); // Non-blocking completion
    }
}
