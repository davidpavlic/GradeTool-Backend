package com.accenture.gradetool.core.generic;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import javax.persistence.Transient;

public interface ParentEntity {

    @Transient
    Set<Supplier<Collection<? extends AbstractEntity>>> getChildrenSuppliers();

}
