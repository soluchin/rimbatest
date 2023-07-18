package com.rimba.technicaltest.Service;


import java.util.List;

import com.rimba.technicaltest.Entity.Transaction;
import com.rimba.technicaltest.Entity.Model.RequestModel.TransactionRequestModel;
import com.rimba.technicaltest.Entity.Model.ResponseModel.TransactionResponseModel;
import com.rimba.technicaltest.Exception.TransactionQtyMoreThanProductQtyException;

public interface ITransactionService {
    List<Transaction> getAllTransaction();
    TransactionResponseModel getTransactionDetail(Integer id);
    Boolean createTransaction(TransactionRequestModel model) throws TransactionQtyMoreThanProductQtyException;
}
