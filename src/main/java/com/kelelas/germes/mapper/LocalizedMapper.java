package com.kelelas.germes.mapper;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class LocalizedMapper<E, T> {

    public T dtoMapper(E entity) {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.equals(Locale.ENGLISH)) {
            return toEngDto(entity);
        }
        return toUkrDto(entity);
    }

    public List<T> listOfDtoMapper(Collection<E> entities) {
        return entities.stream()
                .map(this::dtoMapper)
                .collect(Collectors.toList());
    }

    protected abstract T toEngDto(E entity);

    protected abstract T toUkrDto(E entity);
}