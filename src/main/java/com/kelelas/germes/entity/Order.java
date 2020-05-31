package com.kelelas.germes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="orders",
        uniqueConstraints={@UniqueConstraint(columnNames={"name_eng"})})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "name_eng", nullable = false)
    private String nameEng;
    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "group_id", nullable = false)
    private int groupId;
}
