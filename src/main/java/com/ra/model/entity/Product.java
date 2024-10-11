package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long productId;
    @Column(name="product_description",columnDefinition = "text")
    private String prDescription;
    @Column(name="image",columnDefinition = "varchar(255)")
    private String image;
    @Column(name="product_name",columnDefinition = "varchar(255)")
    private String productName;
    @Column(name="price")
    private Double price;
    @Column(name="status")
    private boolean status=true;
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Category category;


}
