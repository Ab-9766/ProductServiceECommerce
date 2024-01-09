package com.example.productservicedec20.ControllerAdvices;

import com.example.productservicedec20.DTOs.ArithmeticExceptionDTO;
import com.example.productservicedec20.DTOs.ExceptionDTO;
import com.example.productservicedec20.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

@ExceptionHandler(ArithmeticException.class)
public ResponseEntity<ArithmeticExceptionDTO> handleArithmeticException(){
    ArithmeticExceptionDTO dto= new ArithmeticExceptionDTO();
    dto.setMessage("Something has gone wrong");
    return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException exception){
        ExceptionDTO dto= new ExceptionDTO();
        dto.setMessage(exception.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.OK);
            }
        }

