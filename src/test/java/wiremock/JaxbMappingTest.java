package wiremock;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JaxbMappingTest {

    XmlMapper xmlMapper;

    @Before
    public void setUp() {
        xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
        AnnotationIntrospector intr = new JaxbAnnotationIntrospector(xmlMapper.getTypeFactory());
        xmlMapper.setAnnotationIntrospector(intr);
        xmlMapper.enable(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME);
    }

    @Test
    public void testAccountMapping() throws IOException {
        String accountAsXml = "" +
                "<Account>" +
                "   <Number>1234567899</Number>" +
                "   <Name>My name</Name>" +
                "</Account>";

        Account result = xmlMapper.readValue(accountAsXml, Account.class);

        assertThat(result.getNumber(), equalTo("1234567899"));
        assertThat(result.getName(), equalTo("My name"));
    }


}
