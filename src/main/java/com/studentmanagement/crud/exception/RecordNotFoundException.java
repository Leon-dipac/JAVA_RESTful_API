/*
    We have already defined the Model and Repository.
We will later create the Service to perform the CRUD operations (Create, Retrieve, Update , Delete ).
The service class will throw the RecordNotFoundException in case the record does not exist in the Database.

Below is the RecordNotFoundException.java class inside the package com.techblogstation.exception :

*/

package com.studentmanagement.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
    private String exceptionDetail;
    private Object fieldValue;

    public RecordNotFoundException(String exceptionDetail, Long fieldValue){

        super(exceptionDetail+" - "+fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;

    }

    // Getters methods for the above fields
    public String getExceptionDetail(){
        return exceptionDetail;
    }

    public Object getFieldValue(){
        return fieldValue;
    }
}

/*
    Above class has simple Java code to create the custom exception in Java.
We have annotated our above exception class with @ResponseStatus.
This annotation tells Spring Boot to respond with the specified HTTP status code in case the exception is thrown.

In Our example, we have thrown HttpStatus.NOT_FOUND.

*/