package com.rmms.RMMS.dataClasses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MaterialUpdates {
	private Long shipmentId;
	private long materialId;
	private int units;
	private LocalDateTime dateAdded;
	private UsageStatus status;
	enum UsageStatus { USED, WASTED, UNUSED; }
}
