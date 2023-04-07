package com.fenrir.colorme;

import com.fenrir.colorme.shared.PostgreSQLContainerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = {PostgreSQLContainerInitializer.class})
class ColormeApplicationTests {

    @Test
    void contextLoads() {
    }

}
