package ir.bigz.meter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MicroMeterUtils {

    private final Counter visitCounter;
    private final AtomicInteger userSubscribe = new AtomicInteger(0);

    public MicroMeterUtils(MeterRegistry registry) {
        visitCounter = Counter.builder("sample_visit_counter")
                .description("Number of visits to the site")
                .register(registry);

        Gauge.builder("sample_gauge_meter", this.userSubscribe, Number::intValue)
                .description("Count of user")
                .register(registry);
    }

    public Counter getVisitCounter() {
        return visitCounter;
    }

    public AtomicInteger getUserSubscribe() {
        return userSubscribe;
    }
}
