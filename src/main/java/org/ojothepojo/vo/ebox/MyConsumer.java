package org.ojothepojo.vo.ebox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyConsumer {

    public void consumeSingle(Message<String> stringMessage) {
        log.info("Received single message with payload '{}'", stringMessage.getPayload());
    }

    public void consumeBatch1(List<Message<String>> messages) {
        // This is not called by spring cloud stream.
        log.info("Batch 1: Received {} batched messages.", messages.size());
    }

    public void consumeBatch2(Message<List<String>> messages) {
        log.info("Batch 2: Received {} batched messages.", messages.getPayload().size());
        throw new RuntimeException("Error in consumer...");
        // here we already have unexpected behaviour. The messages do end up on the DLQ but there is a cloud internal error
        // Caused by: java.lang.ClassCastException: class java.util.ArrayList cannot be cast to class org.springframework.amqp.core.Message

        // If there is a DLQ TTL defined, the message from the DLQ is put back in the original queue. That fails again and
        // we would expect a loop here. However, the second time the message fails, it gets discarded! That should not happen.
        // x-death header detected on a message with a fatal exception; perhaps requeued from a DLQ? - discarding: (...)
    }

    public void consumeBatch3(List<String> strings) {
        // This is not called by spring cloud stream.
        log.info("Batch 3: Received {} batched messages.", strings.size());
    }
}
