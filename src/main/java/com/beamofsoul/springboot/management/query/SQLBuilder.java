package com.beamofsoul.springboot.management.query;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SQLBuilder {

	public static String buildDynamicSql(String sql, QueryCriteria criteria, Map<String,Object> params) {
		//need to be re-factored
		String unOrderedSql = buildUnOrderedSql(sql, criteria, params);
		return buildOrderedSql(unOrderedSql, criteria);
	}
	
	private static String buildUnOrderedSql(String sql, QueryCriteria criteria, Map<String, Object> params) {
		StringBuffer whereClause = new StringBuffer(QueryCriteria.getWhereClauseBase());
		int index = 1;
		for (SubCriteria sc : criteria.getSubCriterias()) {
			whereClause
				.append(sc.getRelation().getCode())
				.append(getTableAlias(sc))
				.append(sc.getAttribute())
				.append(QueryCriteria.getBlankSpace())
				.append(sc.getOperator().getCode());
			
			if (Operator.IN.equals(sc.getOperator())) {
				Object[] paramValues = (Object[]) sc.getValue();
				whereClause.append(QueryCriteria.getLeftBracket());
				for (int i = 0; i < paramValues.length; i++) {
					whereClause.append(QueryCriteria.getColon());
					String key = QueryCriteria.getQuestionMark() + (index++);
					whereClause.append(key);
					if (i != paramValues.length - 1)
						whereClause.append(QueryCriteria.getComma());
					params.put(key, paramValues[i]);
				}
				whereClause.append(QueryCriteria.getRightBracket());
			} else {
				whereClause.append(QueryCriteria.getColon());
                String key = QueryCriteria.getQuestionMark() + (index++);
                whereClause.append(key);
                params.put(key, sc.getValue());
			}
		}
		return sql.replace(QueryCriteria.getStandin(), whereClause.toString());
	}

	private static String getTableAlias(SubCriteria sc) {
		return StringUtils.isBlank(sc.getTableAlias()) ? "" : (sc.getTableAlias() + QueryCriteria.getDot());
	}

	private static String buildOrderedSql(String unOrderedSql, QueryCriteria criteria) {
		return unOrderedSql;
	}
}
