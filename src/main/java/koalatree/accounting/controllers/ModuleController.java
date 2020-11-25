package koalatree.accounting.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/")
    public String getAppName() {
        return appName;
    }
}
