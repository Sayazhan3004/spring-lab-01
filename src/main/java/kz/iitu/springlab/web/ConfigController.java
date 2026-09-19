package kz.iitu.springlab.web;

import kz.iitu.springlab.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final AppProperties appProperties;

    public ConfigController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping
    public Map<String, Object> getConfig() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("owner", appProperties.getOwner());
        result.put("environment", appProperties.getEnvironment());
        result.put("maxFileSize", appProperties.getUpload().getMaxFileSize());
        result.put("allowedExtensions",
                appProperties.getUpload().getAllowedExtensions());

        return result;
    }
}