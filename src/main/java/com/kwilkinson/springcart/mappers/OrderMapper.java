package com.kwilkinson.springcart.mappers;

import com.kwilkinson.springcart.dtos.OrderDto;
import com.kwilkinson.springcart.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
