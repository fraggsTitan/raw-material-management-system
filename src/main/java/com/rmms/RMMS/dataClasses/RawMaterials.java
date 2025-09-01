package com.rmms.RMMS.dataClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class RawMaterials {
	private Long materialId;
	private Long restId;
	private String materialName;
	private String unitName;
	private Integer threshold;
	private Duration expiryPeriod;
	private Integer currentStock;
	private boolean autoRestock;
	private Duration restockPeriod;
	private Integer reorderQuantity;
	public static class RawMaterialsRowMapper implements RowMapper<RawMaterials>{

		@Override
		public RawMaterials mapRow(ResultSet rs, int rowNum) throws SQLException {
			RawMaterials material=new RawMaterials();
			material.setMaterialId(rs.getLong("material_id"));
			material.setRestId(rs.getLong("rest_id"));
			material.setMaterialName(rs.getString("material_name"));
			material.setUnitName(rs.getString("unit_name"));
			material.setThreshold(rs.getInt("threshold"));
			material.setExpiryPeriod(Duration.ofSeconds(rs.getObject("expiry_period",Duration.class).toSeconds()));
			material.setCurrentStock(rs.getInt("current_stock"));
			material.setAutoRestock(rs.getBoolean("auto_restock"));
			material.setRestockPeriod(Duration.ofSeconds(rs.getObject("restock_period",Duration.class).toSeconds()));
			material.setReorderQuantity(rs.getInt("reorder_quantity"));
			return material;
		}
		
	}
}
