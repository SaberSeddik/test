package wiremock;

import org.springframework.web.client.RestTemplate;

public class MyRestTemplateClient {
    private final RestTemplate restTemplate;
    private final String ressourceUrl;

    public MyRestTemplateClient(RestTemplate restTemplate, String ressourceUrl) {
        this.restTemplate = restTemplate;
        this.ressourceUrl = ressourceUrl;
    }

    public Account getAccount(String accountNumber) {
        return restTemplate.getForObject(ressourceUrl + "/" + accountNumber, Account.class);
    }
}
