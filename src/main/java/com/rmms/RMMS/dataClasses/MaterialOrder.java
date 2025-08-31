package com.rmms.RMMS.dataClasses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MaterialOrder {
	private Long orderId;
	private long materialId;
	private long supplierId;
	private LocalDateTime placeDate;
	private LocalDateTime receiveDate;
	private int orderedUnits;
	private int recievedUnits;
	private DeliveryStatus status;
	enum DeliveryStatus{
		ORDERED,DISPATCHED,DELIVERED;
	}
}

