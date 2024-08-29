package com.santhuuu.microservices.order_service.service;

import com.santhuuu.microservices.order_service.client.InventoryClient;
import com.santhuuu.microservices.order_service.dto.OrderRequest;
import com.santhuuu.microservices.order_service.model.Order;
import com.santhuuu.microservices.order_service.repository.OrderRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRespository orderRespository;

    @Autowired
    private InventoryClient inventoryClient;

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
        }
        else {
            throw new RuntimeException("Product with skucode: "+orderRequest.skuCode()+ " is not in stock");
        }

    }
}
