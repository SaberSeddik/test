package wiremock;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class MyRestTemplateClient {
    private final RestTemplate restTemplate;
    private final String ressourceUrl;

    public MyRestTemplateClient(RestTemplate restTemplate, String ressourceUrl) {
        this.restTemplate = restTemplate;
        this.ressourceUrl = ressourceUrl;
        restTemplate.getMessageConverters().stream()
                .filter(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2XmlHttpMessageConverter)
                .forEach(converter -> {
                    ObjectMapper mapper = ((MappingJackson2XmlHttpMessageConverter) converter).getObjectMapper();
                    AnnotationIntrospector intr = new JaxbAnnotationIntrospector(mapper.getTypeFactory());
                    mapper.setAnnotationIntrospector(intr);
                    mapper.enable(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME);
                });
    }

    public Account getAccount(String accountNumber) {
        return restTemplate.getForObject(ressourceUrl + "/" + accountNumber, Account.class);
    }
}
