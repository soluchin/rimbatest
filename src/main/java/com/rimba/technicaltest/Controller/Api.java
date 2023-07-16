package com.rimba.technicaltest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimba.technicaltest.Entity.Product;
import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.Model.RequestModel.ProductRequestModel;
import com.rimba.technicaltest.Entity.Model.RequestModel.TransactionRequestModel;
import com.rimba.technicaltest.Entity.Model.ResponseModel.TransactionResponseModel;
import com.rimba.technicaltest.Service.ICustomerService;
import com.rimba.technicaltest.Service.IProductService;
import com.rimba.technicaltest.Service.ITransactionService;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class Api {
    @Autowired
    ICustomerService _customerService;

    @Autowired
    IProductService _productService;

    @Autowired
    ITransactionService _transactionService;

    @GetMapping("products")
    @ResponseBody
    public List<Product> getAllProducts(){
        return _productService.getAllProduct();
    }

    @PostMapping("products/upsert")
    @ResponseBody
    public Boolean upsertProducts(@RequestBody ProductRequestModel model){
        try{
            _productService.upsert(model);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @GetMapping("transaction")
    @ResponseBody
    public List<Transaction> getAllTransaction(){
        return _transactionService.getAllTransaction();
    }

    @GetMapping("transaction/{id}")
    @ResponseBody
    public TransactionResponseModel getTransactionDetails(@PathVariable Integer id){
        return _transactionService.getTransactionDetail(id);
    }

    @PostMapping("transaction/create")
    @ResponseBody
    public Boolean createTransaction(@RequestBody TransactionRequestModel model){
        try{
            _transactionService.createTransaction(model);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
