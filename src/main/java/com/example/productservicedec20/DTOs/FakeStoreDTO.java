package com.example.productservicedec20.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
