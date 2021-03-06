package com.kelelas.germes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="history",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "users_id", nullable = false)
    private Long userId;
    @Column(name = "status_id", nullable = false)
    private Long statusId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_history",
            joinColumns = {
                    @JoinColumn(name = "history_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Order> orders = new ArrayList<>();
}
