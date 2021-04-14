package lambda.projects.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hasadvanceamount", "hasordamount"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double advanceamount;
    private double ordamount;
    private String orderdescription;

    @Transient
    public boolean hasadvanceamount = false;
    @Transient
    public boolean hasordamount = false;

    @ManyToMany()
    @JoinTable(name = "orderspayments",
        joinColumns = @JoinColumn(name="ordnum"),
            inverseJoinColumns = @JoinColumn(name="paymentid"))
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "custcode")
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Customer customer;

    public Order() {
    }

    public Order(double advanceamount, double ordamount, Customer customer, String orderdescription) {
        this.advanceamount = advanceamount;
        this.ordamount = ordamount;
        this.customer = customer;
        this.orderdescription = orderdescription;

    }

    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        hasadvanceamount = true;
        this.advanceamount = advanceamount;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        hasordamount = true;
        this.ordamount = ordamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
