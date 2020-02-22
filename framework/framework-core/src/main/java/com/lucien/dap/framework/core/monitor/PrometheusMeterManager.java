package com.lucien.dap.framework.core.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class PrometheusMeterManager {
    private static PrometheusMeterRegistry registry = null;

    private static void init() {
        registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }

    public static PrometheusMeterRegistry register() {
        if (registry == null) {
            init();
        }
        return registry;
    }

    public static void exceptionCounterIncrement(String className, String methodName, String exceptionMessage) {
        Counter post_request_number = register().counter("cdsp_application_exception", "class", className, "method", methodName, "code", exceptionMessage);
        post_request_number.increment();
    }
}
