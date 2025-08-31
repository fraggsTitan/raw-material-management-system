package com.rmms.RMMS.dataClasses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Wastage {
	private Long wasteId;
	private LocalDateTime expiryDate;
	private int stockWasted;
	private long materialId;
	private long shipmentId;
}
