package com.santhuuu.microservices.product_service.dto;

import java.math.BigDecimal;

public record ProductResponse(int id, String name, String description, BigDecimal price) {
}
