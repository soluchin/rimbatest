package com.rimba.technicaltest.Exception;

public class MyTransactionException extends Exception { 
    public MyTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
