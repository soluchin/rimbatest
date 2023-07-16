package com.rimba.technicaltest.Service.Implements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimba.technicaltest.Entity.Product;
import com.rimba.technicaltest.Entity.Model.RequestModel.ProductRequestModel;
import com.rimba.technicaltest.Repository.ProductRepo;
import com.rimba.technicaltest.Service.IProductService;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepo _productRepo;

    @Override
    public List<Product> getAllProduct() {
        return _productRepo.findAll();
    }

    @Override
    public Boolean upsert(ProductRequestModel model) {
        var products = model.getProducts();
        List<Integer> productsIds = products.stream().map(item -> item.getId()).collect(Collectors.toList());
        
        List<Product> oldProducts = _productRepo.findAll();
        List<Integer> oldProductsIds = oldProducts.stream().map(item -> item.getId()).collect(Collectors.toList());

        List<Product> dataToInsert = model.getProducts().stream().filter(item -> item.getId() == null).toList();
        List<Product> dataToUpdate = model.getProducts().stream().filter(item -> item.getId() != null).toList();
        List<Integer> dataToDeleteIds = new ArrayList<Integer>(oldProductsIds);
        dataToDeleteIds.removeAll(productsIds);
        dataToDeleteIds.removeAll(Collections.singleton(null));

        if(!dataToInsert.isEmpty()) InsertAll(dataToInsert);
        if(!dataToUpdate.isEmpty()) UpdateAll(dataToUpdate);
        if(!dataToDeleteIds.isEmpty()) DeleteAll(dataToDeleteIds);
        return true;
    }

    private void InsertAll(List<Product> products){
        _productRepo.saveAll(products);
    }

    private void UpdateAll(List<Product> products){
        for (Product product : products) {
            Product existProduct = _productRepo.getOne(product.getId());
            existProduct.setName(product.getName());
            existProduct.setQty(product.getQty());
            existProduct.setQtyType(product.getQtyType());
            existProduct.setPrice(product.getPrice());
            _productRepo.save(existProduct);
        }
    }

    private void DeleteAll(List<Integer> ids){
        _productRepo.deleteAllById(ids);
    }
}
