package com.rmms.RMMS.dataClasses;
import lombok.Data;
import java.util.*;

@Data
public class Suppliers {
	private Long supplieId;
	private String supplierName;
	private List<String> phones;
}
