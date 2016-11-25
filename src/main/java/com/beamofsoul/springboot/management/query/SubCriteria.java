package com.beamofsoul.springboot.management.query;

import java.util.ArrayList;
import java.util.List;

public class SubCriteria {

	private String attribute;
	private Operator operator;
	private Object value;
	private Relation relation;
	private String tableAlias;
	
	private List<SubCriteria> list;
	
	public SubCriteria(String attribute, Operator operator, Object value,
			Relation relation, String tableAlias) {
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
		this.relation = relation;
		this.tableAlias = tableAlias;
		this.list = new ArrayList<SubCriteria>();
	}

	public SubCriteria(String attribute, Operator operator, Object value,
			Relation relation, String tableAlias, List<SubCriteria> list) {
		this.attribute = attribute;
		this.operator = operator;
		this.value = value;
		this.relation = relation;
		this.tableAlias = tableAlias;
		this.list = list;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public List<SubCriteria> getList() {
		return list;
	}

	public void setList(List<SubCriteria> list) {
		this.list = list;
	}
}
