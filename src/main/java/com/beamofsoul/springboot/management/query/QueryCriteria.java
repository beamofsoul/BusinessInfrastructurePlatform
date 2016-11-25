package com.beamofsoul.springboot.management.query;

import java.util.ArrayList;
import java.util.List;

public class QueryCriteria {

	private List<SubCriteria> subCriterias;
	
	public static String getWhereClauseBase() {
		return " WHERE 1=1 ";
	}

	public List<SubCriteria> getSubCriterias() {
		return subCriterias;
	}

	public void setSubCriterias(List<SubCriteria> subCriterias) {
		this.subCriterias = subCriterias;
	}
	
	public QueryCriteria() {
		this.subCriterias = new ArrayList<SubCriteria>();
	}

	public QueryCriteria(List<SubCriteria> subCriterias) {
		super();
		this.subCriterias = subCriterias;
	}

	public List<SubCriteria> add(SubCriteria sc) {
		subCriterias.add(sc);
		return subCriterias;
	}
	
	public static String getStandin() {
		return "%WHERE_CLAUSE%";
	}
	
	public static String getLeftBracket() {
		return "(";
	}
	
	public static String getRightBracket() {
		return ")";
	}
	
	public static String getComma() {
		return ",";
	}
	
	public static String getQuestionMark() {
		return "?";
	}
	
	public static String getColon() {
		return ":";
	}
	
	public static String getBlankSpace() {
		return " ";
	}
	
	public static String getDot() {
		return ".";
	}
}
