package com.kelelas.germes.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String nameUkr;
    private String nameEng;
    private String email;
    private String password;
}
