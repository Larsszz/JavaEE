package DAOPack.EnityClasses;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchangerate")

public class ExchangeRate {

    @Id
    private String currency;
    private int course;

    public ExchangeRate(String currency, int course) {
        this.currency = currency;
        this.course = course;
    }

    public ExchangeRate() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
