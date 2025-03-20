package com.gklyphon.sabor_digital.waiter.application.mapper;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Waiter} and {@link WaiterDto}.
 * Uses MapStruct to generate mapping implementations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Mapper(componentModel = "spring")
public interface IMapper {

    /**
     * Converts a {@link Waiter} entity to a {@link WaiterDto}.
     *
     * @param waiter The entity to be converted.
     * @return The corresponding DTO.
     */
    WaiterDto fromWaiterToWaiterDto(Waiter waiter);

    /**
     * Converts a {@link WaiterDto} to a {@link Waiter} entity.
     * Inherits the inverse configuration of {@link #fromWaiterToWaiterDto(Waiter)}.
     *
     * @param waiterDto The DTO to be converted.
     * @return The corresponding entity.
     */
    @InheritInverseConfiguration
    Waiter fromWaiterDtoToWaiter(WaiterDto waiterDto);
}
