package com.example.naumenwebproject.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderItemDto {
    private Long id;
    private LocalDateTime expireTime;
    private Integer quantity;
    private CarDto carDto;

}
