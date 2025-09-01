package com.rmms.RMMS.services;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.function.Supplier;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.rmms.RMMS.dataClasses.Suppliers;

@Service
public class SuppliersService {
	
	private JdbcTemplate jdbcTemplate;

	public SuppliersService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void save(Suppliers suppliers) {//create
		KeyHolder keyHolder= new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{
			PreparedStatement ps=connection.prepareStatement("INSERT INTO suppliers(supplier_name) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,suppliers.getSupplierName());
			return ps;
		},keyHolder);
		long supplierId=keyHolder.getKey().longValue();
		for(String phoneNumber:suppliers.getPhones()) {
			jdbcTemplate.update("INSERT INTO contacts (supplier_id,phone_number) VALUES (?,?)",supplierId,phoneNumber);
		}
	}
	public void updateRestaurantSupplier(long supplierId,long restaurantId) {
		jdbcTemplate.update("INSERT INTO restaurant_supplier(supplier_id,restaurant_id) VALUES (?,?)",supplierId,restaurantId);
	}
	public Suppliers findById(long supplierId) {
		return jdbcTemplate.queryForObject("SELECT * FROM suppliers WHERE supplier_id=?",new Suppliers.SuppliersRowMapper(),supplierId);
	}

}
