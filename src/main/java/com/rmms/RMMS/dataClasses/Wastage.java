package com.rmms.RMMS.dataClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class Wastage {
	private Long wasteId;
	private LocalDateTime expiryDate;
	private Integer stockWasted;
	private Long materialId;
	private Long shipmentId;
	public static class WastageRowMapper implements RowMapper<Wastage>{

		@Override
		public Wastage mapRow(ResultSet rs, int rowNum) throws SQLException {
			Wastage wastage=new Wastage();
			wastage.setWasteId(rs.getLong("waste_id"));
			wastage.setExpiryDate(rs.getObject("expiry_date",LocalDateTime.class));
			wastage.setStockWasted(rs.getInt("stock_wasted"));
			wastage.setMaterialId(rs.getLong("material_id"));
			wastage.setShipmentId(rs.getLong("shipment_id"));
			return wastage;
		}
		
	}
}
