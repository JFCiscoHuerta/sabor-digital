package com.gklyphon.sabor_digital.order.application.mapper;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapper {

    Order fromOrderDtoToOrder(OrderDto orderDto);

    @InheritInverseConfiguration
    OrderDto fromOrderToOrderDto(Order order);

}
