package wiremock;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okTextXml;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class WireMockTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort());

    MyRestTemplateClient client;
    String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "/myTestService/account";
        client = new MyRestTemplateClient(new RestTemplate(), wireMockRule.url(baseUrl));
    }

    @Test
    public void testRestService() {
        String accountNumber = RandomStringUtils.randomAlphabetic(20);
        wireMockRule.stubFor(restAccountFoundForAccountNumber(accountNumber));

        Account result = client.getAccount(accountNumber);

        assertThat(result.getNumber(), equalTo(accountNumber));
        assertThat(result.getName(), equalTo("My name"));
    }

    private MappingBuilder restAccountFoundForAccountNumber(String accountNumber) {
        final String answer = "" +
                "<Account>" +
                "   <Number>" + accountNumber + "</Number>" +
                "   <Name>My name</Name>" +
                "</Account>";
        return get((urlEqualTo(baseUrl + "/" + accountNumber)))
                .willReturn(okTextXml(answer));
    }
}
