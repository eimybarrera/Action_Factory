package com.IntegrativeProject.ActionFactory.supplierExceptions;

public class SupplierException extends RuntimeException{

    //Se encargará de devolver el mensaje de la excepción
    public SupplierException(String message){
        super(message);
    }
}
