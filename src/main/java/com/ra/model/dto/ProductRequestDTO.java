package com.ra.model.dto;

import com.ra.model.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class ProductRequestDTO {
    private Long productId;
    private String prDescription;
    private MultipartFile image;
    private String productName;
    private Double price;
    private boolean status;

}
