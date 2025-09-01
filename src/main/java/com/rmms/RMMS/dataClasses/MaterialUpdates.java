package com.rmms.RMMS.dataClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

/*Data class for material_updates in SQL shipmentId is Long as it is PK and can be null, LocalDateTime maps to timestamp in SQL,UsageStatus maps to its namesake in SQL*/
@Data
public class MaterialUpdates {
	private Long shipmentId;
	private Long materialId;
	private Integer units;
	private LocalDateTime dateAdded;
	private UsageStatus status;
	enum UsageStatus { USED, WASTED, UNUSED; }
	public static class MaterialUpdatesRowMapper implements RowMapper<MaterialUpdates>{

		@Override
		public MaterialUpdates mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialUpdates materialUpdates=new MaterialUpdates();
			materialUpdates.setShipmentId(rs.getLong("shipment_id"));
			materialUpdates.setMaterialId(rs.getLong("material_id"));
			materialUpdates.setUnits(rs.getInt("units"));
			materialUpdates.setDateAdded(rs.getObject("date_added",LocalDateTime.class));
			materialUpdates.setStatus(UsageStatus.valueOf(rs.getString("status").toUpperCase()));
			return materialUpdates;
		}
		
	}
}
