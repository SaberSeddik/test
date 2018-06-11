package wiremock;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @XmlElement(name = "Number")
    private final String number;
    @XmlElement(name = "Name")
    private final String name;

    //Will be used by jaxb
    private Account() {
        number = null;
        name = null;
    }

    public Account(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
