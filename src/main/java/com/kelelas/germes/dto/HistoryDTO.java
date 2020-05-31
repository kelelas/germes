package com.kelelas.germes.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HistoryDTO {
    private Long id;
    private LocalDateTime date;
    private int price;
    private String status;
    private String userName;
    private List<OrderDTO> orders = new ArrayList<>();
}
