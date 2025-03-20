package com.gklyphon.sabor_digital.order.application.mapper;

import com.gklyphon.sabor_digital.order.application.dtos.OrderDto;
import com.gklyphon.sabor_digital.order.domain.models.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between Order entities and Order DTOs.
 *
 * <p>Utilizes MapStruct for automatic implementation generation.</p>
 *
 * @author JFCiscoHuerta
 * @date 2025/03/16
 */
@Mapper(componentModel = "spring")
public interface IMapper {

    /**
     * Converts an OrderDto object to an Order entity.
     *
     * @param orderDto the DTO to convert
     * @return the corresponding Order entity
     */
    Order fromOrderDtoToOrder(OrderDto orderDto);


    @InheritInverseConfiguration

    /**
     * Converts an Order entity to an OrderDto object.
     *
     * @param order the entity to convert
     * @return the corresponding OrderDto object
     */
    OrderDto fromOrderToOrderDto(Order order);
}
