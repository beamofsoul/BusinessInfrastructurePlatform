package com.beamofsoul.springboot.management.query;

import org.apache.commons.lang3.StringUtils;

public enum Operator {
	EQUALS("="),
	NOT_EQUALS("!="),
	GREATER(">"),
	LESS("<"),
	IN("IN"),
	LIKE("LIKE");
	
	private String code;
	
	private Operator(String code) {
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
	
	public static boolean contains(String name) {
		Operator[] operators = values();
		for (Operator operator : operators) {
			if (operator.name().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
