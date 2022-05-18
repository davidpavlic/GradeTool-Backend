package com.accenture.gradetool.core.generic.dto;

import com.accenture.gradetool.core.generic.AbstractEntity;
import com.accenture.gradetool.core.generic.ParentEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Context;
import org.mapstruct.Named;

public interface DTOMapper<D extends DTO, E extends AbstractEntity> {

    E fromDTO(D dto);

    @Named("default")
    default D toDTO(E entity) {
        return toDTO(entity, false);
    }

    D toDTO(E entity, @Context boolean showDeletedChildren);

    default Collection<D> toDTO(Collection<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toSet());
    }

    default Collection<D> toDTO(Collection<E> entities, boolean showDeletedChildren) {
        return entities.stream().map(entity -> toDTO(entity, showDeletedChildren)).collect(Collectors.toSet());
    }

    @BeforeMapping
    default void filterDeletedChildren(ParentEntity parentEntity, @Context boolean showDeletedChildren) {
        if (!showDeletedChildren) {
            parentEntity.getChildrenSuppliers().forEach(supplier -> supplier.get().removeIf(childEntity -> {
                if (childEntity.isDeleted()) {
                    return true;
                }

                if (childEntity instanceof ParentEntity) {
                    filterDeletedChildren((ParentEntity) childEntity, false);
                }

                return false;
            }));
        }
    }

}
