package com.rimba.technicaltest.Service.Implements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rimba.technicaltest.Entity.Customer;
import com.rimba.technicaltest.Entity.Product;
import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.TransactionItem;
import com.rimba.technicaltest.Entity.Model.RequestModel.TransactionRequestModel;
import com.rimba.technicaltest.Entity.Model.ResponseModel.TransactionResponseModel;
import com.rimba.technicaltest.Exception.MyTransactionException;
import com.rimba.technicaltest.Repository.CustomerRepo;
import com.rimba.technicaltest.Repository.ProductRepo;
import com.rimba.technicaltest.Repository.TransactionItemRepo;
import com.rimba.technicaltest.Repository.TransactionRepo;
import com.rimba.technicaltest.Service.ITransactionService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private TransactionRepo _transactionRepo;
    @Autowired
    private TransactionItemRepo _transactionItemRepo;
    @Autowired
    private ProductRepo _productRepo;
    @Autowired
    private CustomerRepo _customerRepo;

    DateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    @Override
    public List<Transaction> getAllTransaction() {
        return _transactionRepo.findAll();
    }

    public TransactionResponseModel getTransactionDetail(Integer id){
        var response = new TransactionResponseModel();
        response.setTransaction(_transactionRepo.findById(id).get());
        response.setTransactionItem(_transactionItemRepo.findByTransactionId(response.getTransaction().getId()));
        return response;
    }

    @Override
    @Transactional(rollbackFor=MyTransactionException.class)
    public Boolean createTransaction(TransactionRequestModel model) throws MyTransactionException, EntityNotFoundException  {
        
        model.setTransaction(new Transaction());
        model.getTransaction().setCustomerId(model.getCustomerId());
        model.getTransaction().setTransactionDate(new Date());

        Transaction t = _transactionRepo.save(model.getTransaction());

        Customer c = _customerRepo.findById(model.getCustomerId()).get();

        Double totalPrice = 0d;
        for (TransactionItem ti : model.getTransactionItems()){
            Product product = _productRepo.findById(ti.getItemId()).get();
            ti.setPrice(product.getPrice() * ti.getQty());
            
            if(product.getQty() - ti.getQty() < 0){
                throw new MyTransactionException("transaction qty more than product qty");
            }
            //update qty
            product.setQty(product.getQty() - ti.getQty());
            _productRepo.save(product);

            totalPrice += product.getPrice() * ti.getQty();
            ti.setTransactionId(t.getId());
        }

        t.setTotalPrice(totalPrice);

        switch(c.getDiscountType()) {
            case "fix":
                t.setDiscount(c.getDiscount());
                break;
            case "percentage":
                t.setDiscount(totalPrice * c.getDiscount());
                break;
            default:
                t.setDiscount(0d);
          }
        t.setFinalPrice(totalPrice - t.getDiscount());
        t.setTransactionCode("TR"+t.getId()+formatter.format(t.getTransactionDate()));

        _transactionRepo.save(t);
        _transactionItemRepo.saveAll(model.getTransactionItems());
        return true;
    }
}