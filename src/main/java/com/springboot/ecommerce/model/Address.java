package com.springboot.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be atleast 5 characters!!")
    private String streetName;

    @NotBlank
    @Size(min = 5,message = "Building name must be atleast 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 4,message = "City name must be atleast 4 characters")
    private String cityName;

    @NotBlank
    @Size(min = 2,message = "State name must be atleast 2 characters")
    private String stateName;

    @NotBlank
    @Size(min = 2,message = "Country name must be atleast 2 characters")
    private String countryName;

    @NotBlank
    @Size(min = 6,message = "Pincode must be atleast 6 characters")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String streetName, String buildingName, String cityName, String stateName, String countryName, String pincode) {
        this.streetName = streetName;
        this.buildingName = buildingName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.pincode = pincode;
    }
}
