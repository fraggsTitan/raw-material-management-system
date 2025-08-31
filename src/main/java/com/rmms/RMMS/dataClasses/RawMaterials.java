package com.rmms.RMMS.dataClasses;

import java.time.Duration;

import lombok.Data;

@Data
public class RawMaterials {
	private Long materialId;
	private long restId;
	private String materialName;
	private String unitName;
	private int threshold;
	private Duration expiryPeriod;
	private int currentStock;
	private boolean autoRestock;
	private Duration restockPeriod;
	private int reorderQuantity;
}
