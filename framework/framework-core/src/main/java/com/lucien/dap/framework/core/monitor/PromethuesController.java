package com.lucien.dap.framework.core.monitor;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PromethuesController {
    @RequestMapping("/promMetrics")
    public void promMetrics(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        PrometheusMeterRegistry registry = PrometheusMeterManager.register();
        String meterResponse = registry.scrape();
        try {
            httpResponse.getWriter().print(meterResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
