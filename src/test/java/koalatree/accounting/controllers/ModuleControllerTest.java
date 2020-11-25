package koalatree.accounting.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

public class ModuleControllerTest {

    @Value("${spring.application.name}")
    private String appName;

    private ModuleController subject = new ModuleController();

    @Test
    public void appName_shouldReturnAppName() {
        // given

        // when
        String result = subject.getAppName();

        /// then
        assertThat(result).isEqualTo(appName);
    }
}