package com.rmms.RMMS.services;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.rmms.RMMS.dataClasses.RawMaterials;

@Service
public class RawMaterialService {
	/*What happens in the save method here is you can add the raw material details
	private long restId;
	private String materialName;
	private String unitName;
	private int threshold;
	private Duration expiryPeriod;
	private int currentStock;
	private boolean autoRestock;
	private Duration restockPeriod;
	private int reorderQuantity;
	Auto restock will be auto-set to FALSE, can be toggled when nummber of material_updates reaches a certain level
	will need materialname,unitname,threshold,expiryPeriod,currentStock,restockPeriod,restId from customer
	*/
	private JdbcTemplate jdbcTemplate;
	public RawMaterialService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public void save(RawMaterials rawMaterials) {//Create 
		jdbcTemplate.update("""
				INSERT INTO raw_materials(material_name,unit_name,threshold,expiry_period,current_stock,restock_period,rest_id) 
				VALUES(?,?,?,?,?,?,?)""",rawMaterials.getMaterialName(),rawMaterials.getUnitName(),rawMaterials.getThreshold(),rawMaterials.getExpiryPeriod(),
				rawMaterials.getCurrentStock(),rawMaterials.getRestockPeriod(),rawMaterials.getRestId());
	}
	public List< RawMaterials> findByRestId(long restId) {//read
		/*
		 * Will need the following: 
		 Long materialId,	  String materialName; String unitName; int threshold;Duration expiryPeriod; int currentStock;boolean autoRestock; Duration restockPeriod;
		int reorderQuantity;
		 * */
		return jdbcTemplate.query("""
				SELECT * FROM raw_materials
				WHERE rest_id=?""",new RawMaterials.RawMaterialsRowMapper(),restId);
	}
	public RawMaterials findById(long materialId) {//read
		return jdbcTemplate.queryForObject("""
				SELECT * FROM raw_materials WHERE material_id=?
				""",new RawMaterials.RawMaterialsRowMapper(),materialId);
	}
	public void update(RawMaterials rawMaterials) {//update
		jdbcTemplate.update("""
					UPDATE raw_materials SET material_name=?,unit_name=?,threshold=?,expiry_period=?,current_stock=?,auto_restock=?,
					restock_period=?,reorder_quantity=? WHERE material_id=?
				""",rawMaterials.getMaterialName(),rawMaterials.getUnitName(),rawMaterials.getThreshold(),rawMaterials.getExpiryPeriod(),rawMaterials.getCurrentStock()
				,rawMaterials.isAutoRestock(),rawMaterials.getRestockPeriod(),rawMaterials.getReorderQuantity(),rawMaterials.getMaterialId());
	}
	public void delete(long materialId) {//delete
		jdbcTemplate.update("DELETE FROM raw_materials WHERE material_id=?",materialId);
	}
}
