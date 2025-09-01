package com.rmms.RMMS.services;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.rmms.RMMS.dataClasses.Restaurant;

@Service
public class RestaurantService {
	private JdbcTemplate jdbcTemplate;
	public RestaurantService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public void save(Restaurant restaurant) {//create
		jdbcTemplate.update("INSERT INTO restaurants(rest_name,location) VALUES(?,?)",restaurant.getRestName(),restaurant.getLocation());
	}
	public Restaurant findById(Long id) {//read
		return jdbcTemplate.queryForObject("SELECT id,rest_name,location FROM restaurants where id=?",new BeanPropertyRowMapper<Restaurant>(Restaurant.class),id);
	}
	public void update(Restaurant restaurant) {//update
		jdbcTemplate.update("UPDATE restaurants SET rest_name=?,location=? WHERE id=?",restaurant.getRestName(),restaurant.getLocation(),restaurant.getId());
	}
	public void delete(long id) {//delete
		jdbcTemplate.update("DELETE FROM restaurants WHERE id=?",id);
	}//TODO : ADD A METHOD  TO GET SUPPLIERS LINKED WITH RESTAURANTS
}
