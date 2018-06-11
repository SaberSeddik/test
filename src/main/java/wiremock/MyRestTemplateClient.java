package wiremock;

import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MyRestTemplateClient {
    private final RestTemplate restTemplate;
    private final String ressourceUrl;

    public MyRestTemplateClient(RestTemplate restTemplate, String ressourceUrl) {
        this.restTemplate = restTemplate;
        this.ressourceUrl = ressourceUrl;
        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        MappingJackson2XmlHttpMessageConverter messageConverter = new MappingJackson2XmlHttpMessageConverter();
        messageConverter.getObjectMapper().registerModule(jaxbAnnotationModule);
        restTemplate.getMessageConverters().add(messageConverter);
    }

    public Account getAccount(String accountNumber) {
        return restTemplate.getForObject(ressourceUrl + "/" + accountNumber, Account.class);
    }
}
