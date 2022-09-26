package com.unholysoftware.emanuel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "delivered_at")
    private Date deliveredAt;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "costumer_id", nullable = false)
    private Costumer costumer;

    @OneToMany(mappedBy = "contract")
    private Set<Request> requests;

    @OneToMany(mappedBy = "contract")
    private Set<Payment> payments;

    @OneToMany(mappedBy = "contract")
    private Set<Ring> rings;

    public Contract() {
    }

    public Contract(long id, Date createdAt, Date deliveredAt, String status, Costumer costumer) {
        this.id = id;
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
        this.status = status;
        this.costumer = costumer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", deliveredAt=" + deliveredAt +
                ", status='" + status + '\'' +
                ", costumer=" + costumer +
                '}';
    }
}
