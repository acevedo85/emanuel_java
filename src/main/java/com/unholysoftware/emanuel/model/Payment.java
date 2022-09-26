package com.unholysoftware.emanuel.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    public Payment() {
    }

    public Payment(long id, float amount, Date paymentDate, Contract contract) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", contract=" + contract +
                '}';
    }
}
