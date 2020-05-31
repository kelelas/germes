package com.kelelas.germes.mapper;

import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.dto.HistoryDTO;
import com.kelelas.germes.entity.Order;
import com.kelelas.germes.entity.History;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LocaleHistoryMapper extends LocalizedMapper<History, HistoryDTO> {
    @Override
    protected HistoryDTO toEngDto(History entity) {
        HistoryDTO historyDTO = HistoryDTO.builder()
                .date(entity.getDate())
                .id(entity.getId())
                .price(entity.getPrice())
                .status(entity.getStatus().getStatusEng())
                .userName(entity.getUser().getNameEng())
                .orders(new ArrayList<>())
                .build();
        for (Order order : entity.getOrders()){
            OrderDTO orderDTO = OrderDTO.builder()
                    .price(order.getPrice())
                    .name(order.getNameEng())
                    .image(order.getImage())
                    .id(order.getId())
                    .build();
            historyDTO.getOrders().add(orderDTO);
        }
        return  historyDTO;
    }

    @Override
    protected HistoryDTO toUkrDto(History entity) {
        HistoryDTO historyDTO = HistoryDTO.builder()
                .date(entity.getDate())
                .id(entity.getId())
                .price(entity.getPrice())
                .status(entity.getStatus().getStatusUkr())
                .userName(entity.getUser().getNameUkr())
                .orders(new ArrayList<>())
                .build();
        for (Order order : entity.getOrders()){
            OrderDTO orderDTO = OrderDTO.builder()
                    .price(order.getPrice())
                    .name(order.getNameUkr())
                    .image(order.getImage())
                    .id(order.getId())
                    .build();
            historyDTO.getOrders().add(orderDTO);
        }
        return  historyDTO;
    }
}
