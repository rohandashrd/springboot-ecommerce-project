package com.springboot.ecommerce.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addressId;

    private String streetName;

    private String buildingName;

    private String cityName;

    private String stateName;

    private String countryName;

    private String pincode;
}
