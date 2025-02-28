package com.gklyphon.sabor_digital.waiter.application.mapper;

import com.gklyphon.sabor_digital.waiter.application.dto.WaiterDto;
import com.gklyphon.sabor_digital.waiter.domain.models.Waiter;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapper {

    WaiterDto fromWaiterToWaiterDto(Waiter waiter);

    @InheritInverseConfiguration
    Waiter fromWaiterDtoToWaiter(WaiterDto waiterDto);
}
