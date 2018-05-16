package org.ko.framework.waremock;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {

    @Test
    public void mockServer () throws IOException {
        configureFor(8080);
        removeAllMappings();
        mock("/order/1", "01");
        mock("/user", "02");
        mock("/user/1", "03");
    }

    private void mock (String url, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/resource/" + fileName +".json");
        String content = FileUtils.readFileToString(resource.getFile(), "UTF-8");
        stubFor(get(urlEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }
}
