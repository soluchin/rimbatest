package com.rimba.technicaltest.Exception;

public class TransactionQtyMoreThanProductQtyException extends Exception { 
    public TransactionQtyMoreThanProductQtyException(String errorMessage) {
        super(errorMessage);
    }
}
