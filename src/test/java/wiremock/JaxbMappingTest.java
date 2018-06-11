package wiremock;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
