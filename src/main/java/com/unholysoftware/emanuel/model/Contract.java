package com.unholysoftware.emanuel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contracts")
@Getter @Setter @NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Contract(Date createdAt, Date deliveredAt, String status, Costumer costumer) {
        this.createdAt = createdAt;
        this.deliveredAt = deliveredAt;
        this.status = status;
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
