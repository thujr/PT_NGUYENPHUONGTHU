package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int categoryId;
    @Column(name="category_name",columnDefinition = "varchar(100)",nullable = false)
    private String categoryName;
    @Column(name="description", columnDefinition = "text")
    private String caDescription;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
