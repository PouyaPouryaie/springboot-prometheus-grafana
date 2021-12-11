package ir.bigz.meter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    final MicroMeterUtils microMeterUtils;

    public SampleController(MicroMeterUtils microMeterUtils) {
        this.microMeterUtils = microMeterUtils;
    }

    @GetMapping
    public ResponseEntity<?> helloWorld(){
        microMeterUtils.getVisitCounter().increment();
        log.info("Simple log statement, visit count {}", microMeterUtils.getVisitCounter().count());
        return ResponseEntity.ok().body("hello world");
    }
}
