package com.accenture.gradetool.util;

import com.accenture.gradetool.core.generic.AbstractEntity;
import java.util.Collection;
import java.util.stream.Stream;

public final class MapperUtil {

    private MapperUtil() {
    }

    public static <T extends AbstractEntity> Stream<T> filterDeleted(Collection<T> entities) {
        return filterDeleted(entities.stream());
    }

    public static <T extends AbstractEntity> Stream<T> filterDeleted(Stream<T> stream) {
        return stream.filter(entity -> !entity.isDeleted());
    }

}
