package com.gklyphon.sabor_digital.table.application.mapper;

import com.gklyphon.sabor_digital.table.application.dtos.TableDto;
import com.gklyphon.sabor_digital.table.domain.models.Table;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Table} and {@link TableDto}.
 * Uses MapStruct to generate mapping implementations.
 *
 * @author JFCiscoHuerta
 * @date 2025/03/20
 */
@Mapper(componentModel = "spring")
public interface IMapper {

    /**
     * Converts a {@link TableDto} to a {@link Table} entity.
     *
     * @param tableDto The TableDto object to be converted.
     * @return The corresponding Table entity.
     */
    Table fromTableDtoToTable(TableDto tableDto);

    /**
     * Converts a {@link Table} entity to a {@link TableDto}.
     *
     * @param table The Table entity to be converted.
     * @return The corresponding TableDto object.
     */
    @InheritInverseConfiguration
    TableDto fromTableToTableDto(Table table);
}
