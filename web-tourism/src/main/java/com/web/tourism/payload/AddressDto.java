package com.web.tourism.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private Long addressId;

    private String userStreet;

    private String userState;

    private String userCity;

    private String userCountry;

    private String zipCode;

    private Date createDate;

    private Date modifiedDate;

}
