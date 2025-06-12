package com.kwilkinson.springcart.mappers;

import com.kwilkinson.springcart.dtos.CartDto;
import com.kwilkinson.springcart.dtos.CartItemDto;
import com.kwilkinson.springcart.entities.Cart;
import com.kwilkinson.springcart.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
