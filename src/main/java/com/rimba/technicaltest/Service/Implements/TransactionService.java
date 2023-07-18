package com.rimba.technicaltest.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rimba.technicaltest.Entity.Product;
import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.TransactionItem;
import com.rimba.technicaltest.Entity.Model.RequestModel.TransactionRequestModel;
import com.rimba.technicaltest.Entity.Model.ResponseModel.TransactionResponseModel;
import com.rimba.technicaltest.Exception.TransactionQtyMoreThanProductQtyException;
import com.rimba.technicaltest.Repository.ProductRepo;
import com.rimba.technicaltest.Repository.TransactionItemRepo;
import com.rimba.technicaltest.Repository.TransactionRepo;
import com.rimba.technicaltest.Service.ITransactionService;


@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private TransactionRepo _transactionRepo;
    @Autowired
    private TransactionItemRepo _transactionItemRepo;
    @Autowired
    private ProductRepo _productRepo;

    @Override
    public List<Transaction> getAllTransaction() {
        return _transactionRepo.findAll();
    }

    public TransactionResponseModel getTransactionDetail(Integer id){
        var response = new TransactionResponseModel();
        response.setTransaction(_transactionRepo.getOne(id));
        response.setTransactionItem(_transactionItemRepo.findByTransactionId(response.getTransaction().getId()));
        return response;
    }

    @Override
    @Transactional(rollbackFor=TransactionQtyMoreThanProductQtyException.class)
    public Boolean createTransaction(TransactionRequestModel model) throws TransactionQtyMoreThanProductQtyException {
        
        Transaction t = _transactionRepo.save(model.getTransaction());

        Double totalPrice = 0d;
        for (TransactionItem ti : model.getTransactionItem()){
            Product product = _productRepo.getOne(ti.getItemId());

            if(product.getQty() - ti.getQty() < 0){
                throw new TransactionQtyMoreThanProductQtyException("transaction qty more than product qty");
            }
            //update qty
            product.setQty(product.getQty() - ti.getQty());
            _productRepo.save(product);

            totalPrice += ti.getPrice() * ti.getQty();
            ti.setTransactionId(t.getId());
        }

        t.setTotalPrice(totalPrice);
        t.setDiscount(totalPrice * model.getCustomer().getDiscountRate());
        t.setFinalPrice(totalPrice - t.getDiscount());

        _transactionRepo.save(t);
        _transactionItemRepo.saveAll(model.getTransactionItem());
        return true;
    }
}