package com.santhuuu.microservices.order_service.service;

import com.santhuuu.microservices.order_service.client.InventoryClient;
import com.santhuuu.microservices.order_service.dto.OrderRequest;
import com.santhuuu.microservices.order_service.event.OrderPlacedEvent;
import com.santhuuu.microservices.order_service.model.Order;
import com.santhuuu.microservices.order_service.repository.OrderRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        var isInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if(isInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            orderRespository.save(order);
            log.info("Order placed successfully");

            //TODO: Send the message to kafka Topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), order.getSkuCode());
            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
        }
        else {
            throw new RuntimeException("Product with skucode: "+orderRequest.skuCode()+ " is not in stock");
        }

    }
}
