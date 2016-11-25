package com.beamofsoul.springboot.management.query;

import org.apache.commons.lang3.StringUtils;

public enum Relation {
	AND("AND"),
	OR("OR");
	
	private String code;
	
	private Relation(String code) {
		this.code = code;
	}

	public String getCode() {
		return " " + code + " ";
	}

	public static final Relation getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (Relation item : Relation.values()) {
            if (StringUtils.equals(item.getCode().trim(), code.trim())) {
                return item;
            }
        }
        return null;
    }
}
