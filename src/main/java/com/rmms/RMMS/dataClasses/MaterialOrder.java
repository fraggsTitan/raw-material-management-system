package com.rmms.RMMS.dataClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;
/*Data class for material_order in SQL orderID is Long as it is PK and can be null, LocalDateTime maps to timestamp in SQL,DeliveryStatus maps to its namesake in SQL*/
@Data
public class MaterialOrder {
	private Long orderId;
	private Long materialId;
	private Long supplierId;
	private LocalDateTime placeDate;
	private LocalDateTime receiveDate;
	private Long orderedUnits;
	private Long recievedUnits;
	private DeliveryStatus status;
	enum DeliveryStatus{
		ORDERED,DISPATCHED,DELIVERED;
	}
	/*Could choose to use beanpropertyrowmapper but doing this to keep consistent conversion and control over the schema to object*/
	public static class MaterialOrderRowMapper implements RowMapper<MaterialOrder>{

		@Override
		public MaterialOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialOrder materialOrder=new MaterialOrder();
			materialOrder.setOrderId(rs.getLong("order_id"));
			materialOrder.setMaterialId(rs.getLong("material_id"));
			materialOrder.setSupplierId(rs.getLong("supplier_id"));
			materialOrder.setPlaceDate(rs.getObject("place_date",LocalDateTime.class));
			materialOrder.setReceiveDate(rs.getObject("recieve_date",LocalDateTime.class));
			materialOrder.setOrderedUnits(rs.getLong("ordered_untis"));
			materialOrder.setRecievedUnits(rs.getLong("recieved_units"));
			materialOrder.setStatus(DeliveryStatus.valueOf(rs.getString("status").toUpperCase()));
			return materialOrder;
		}
		
	}
}

