package com.example.naumenwebproject.mapper;

import com.example.naumenwebproject.dto.CarDto;
import com.example.naumenwebproject.dto.OrderItemDto;
import com.example.naumenwebproject.exception.CarDtoNotFoundException;
import com.example.naumenwebproject.exception.CarNotFoundException;
import com.example.naumenwebproject.model.Car;
import com.example.naumenwebproject.model.OrderItem;
import com.example.naumenwebproject.repository.CarRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public OrderItemMapper(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setExpireTime(orderItem.getExpireTime());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setCarDto(carMapper.carToDto(orderItem.getCar()));

        return orderItemDto;
    }

    public OrderItem dtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setExpireTime(orderItemDto.getExpireTime());
        orderItem.setQuantity(orderItemDto.getQuantity());

        CarDto carDto = orderItemDto.getCarDto();
        if (carDto == null) {
            throw new CarDtoNotFoundException("CarDto is null in OrderItemDto");
        }

        Car car = carRepository.findById(orderItemDto.getCarDto().getId())
                .orElseThrow(() -> new CarNotFoundException("Car not found"));

        orderItem.setCar(car);

        return orderItem;
    }

    public List<OrderItemDto> orderItemsToDtoList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::orderItemToDto)
                .collect(Collectors.toList());
    }
}
