package com.testReactivo.testReactivo;

import com.testReactivo.testReactivo.UpperCasePublisher.UppercaseConverter;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class UpperCaseTest {

    @Test
    void uppercaseConverterTest(){
        TestPublisher
                .<String>create()
                .next("Primero", "Segundo", "Tercero")
                .error(new RuntimeException("Message"));
    }
    final TestPublisher<String> testPublisher = TestPublisher.create();
    @Test
    void testUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", null, "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }
    @Test
    void verify(){
        TestPublisher
                .createNoncompliant(TestPublisher.Violation.ALLOW_NULL)
                .emit("1", "2", null, "3");

    }
}
