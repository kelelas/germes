package com.kelelas.germes.mapper;

import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.entity.Order;
import org.springframework.stereotype.Component;


@Component
public class LocaleOrderMapper extends LocalizedMapper<Order, OrderDTO> {
    @Override
    protected OrderDTO toEngDto(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getNameEng())
                .price(entity.getPrice())
                .groupId(entity.getGroupId())
                .build();
    }

    @Override
    protected OrderDTO toUkrDto(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .name(entity.getNameUkr())
                .price(entity.getPrice())
                .groupId(entity.getGroupId())
                .build();
    }
}
