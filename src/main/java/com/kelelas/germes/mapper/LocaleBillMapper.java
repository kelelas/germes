package com.kelelas.germes.mapper;

import com.kelelas.germes.dto.BillDTO;
import com.kelelas.germes.dto.OrderDTO;
import com.kelelas.germes.entity.Bill;
import com.kelelas.germes.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LocaleBillMapper extends LocalizedMapper<Bill, BillDTO> {
    @Override
    protected BillDTO toEngDto(Bill entity) {
        BillDTO billDTO = BillDTO.builder()
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
            billDTO.getOrders().add(orderDTO);
        }
        return  billDTO;
    }

    @Override
    protected BillDTO toUkrDto(Bill entity) {
        BillDTO billDTO = BillDTO.builder()
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
            billDTO.getOrders().add(orderDTO);
        }
        return  billDTO;
    }
}
