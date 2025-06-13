package com.kwilkinson.springcart.controllers;

import com.kwilkinson.springcart.dtos.CheckoutRequest;
import com.kwilkinson.springcart.dtos.CheckoutResponse;
import com.kwilkinson.springcart.dtos.ErrorDto;
import com.kwilkinson.springcart.exceptions.CartEmptyException;
import com.kwilkinson.springcart.exceptions.CartNotFoundException;
import com.kwilkinson.springcart.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
