package com.rimba.technicaltest.Entity.Model.RequestModel;

import java.util.List;

import com.rimba.technicaltest.Entity.Product;

import lombok.Data;

@Data
public class ProductRequestModel {
    List<Product> products;
}
