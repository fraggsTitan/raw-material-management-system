package com.rmms.RMMS.dataClasses;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.core.RowMapper;

@Data
public class Suppliers {
	private Long supplierId;
	private String supplierName;
	private List<String> phones;
	public static class SuppliersRowMapper implements RowMapper<Suppliers>{

		@Override
		public Suppliers mapRow(ResultSet rs, int rowNum) throws SQLException {
			Suppliers suppliers=new Suppliers();
			suppliers.setSupplierId(rs.getLong("supplier_id"));
			suppliers.setSupplierName(rs.getString("supplier_name"));
			/*SET PHONES IN SUPPLIER SERVICE*/
			return suppliers;
		}
		
	}
}
