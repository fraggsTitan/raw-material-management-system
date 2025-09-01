package com.rmms.RMMS.dataClasses;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import lombok.Data;

@Data
public class Restaurant {
	private Long id;
	private String restName;
	private String location;
	public static class RestaurantRowMapper implements RowMapper<Restaurant>{

		@Override
		public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
			Restaurant restaurant=new Restaurant();
			restaurant.setId(rs.getLong("id"));
			restaurant.setRestName(rs.getString("rest_name"));
			restaurant.setLocation(rs.getString("location"));
			return restaurant;
		}
		
	}
}
