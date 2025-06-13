package com.kwilkinson.springcart.services;

import com.kwilkinson.springcart.dtos.CheckoutRequest;
import com.kwilkinson.springcart.dtos.CheckoutResponse;
import com.kwilkinson.springcart.entities.Order;
import com.kwilkinson.springcart.exceptions.CartEmptyException;
import com.kwilkinson.springcart.exceptions.CartNotFoundException;
import com.kwilkinson.springcart.repositories.CartRepository;
import com.kwilkinson.springcart.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;

    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
