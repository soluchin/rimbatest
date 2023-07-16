package com.rimba.technicaltest.Service;

import java.util.List;

import com.rimba.technicaltest.Entity.Product;
import com.rimba.technicaltest.Entity.Model.RequestModel.ProductRequestModel;

public interface IProductService {
    List<Product> getAllProduct();
    Boolean upsert(ProductRequestModel model);
}
