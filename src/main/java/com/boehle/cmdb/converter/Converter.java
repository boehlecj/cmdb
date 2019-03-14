package com.boehle.cmdb.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.boehle.cmdb.dto.AbstractDto;
import com.boehle.cmdb.model.AbstractEntity;

public interface Converter <D extends AbstractDto, E extends AbstractEntity> {
	E fromDto(D dto);
    D fromEntity(E entity);
    
    default List<D> listFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }

    default List<E> listFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }
}
