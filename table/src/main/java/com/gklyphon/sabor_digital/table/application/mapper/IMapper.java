package com.gklyphon.sabor_digital.table.application.mapper;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMapper {

    Table fromTableDtoToTable(TableDto tableDto);

    @InheritInverseConfiguration
    TableDto fromTableToTableDto(Table table);

}
