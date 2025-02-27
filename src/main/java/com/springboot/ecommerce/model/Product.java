package com.springboot.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank
    @Size(min = 3,message = "Product name should contain atleast 3 characters")
    private String productName;

    @NotBlank
    @Size(min = 10,message = "Product description should contain atleast 10 characters")
    private String productDescription;
    private String productImage;
    private Integer productQuantity;
    private double productPrice;
    private double productDiscount;
    private double productSpecialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}