package com.kelelas.germes.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {
        private Long id;
        private String name;
        private int price;
        private String image;
        private int groupId;
}
