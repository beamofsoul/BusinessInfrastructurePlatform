package com.beamofsoul.springboot.management.query;

public class CriteriaBuilder {
	
	public final static QueryCriteria QUERY_CRITERIA = new QueryCriteria();

//	public QueryCriteria buildQueryCriteria(String buildSearchType) {
//		QueryCriteria query = new QueryCriteria();
//		operationMap = this.getOperationMap();
//		try {
//			for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
//				String paramKey = entry.getKey();
//				Object value = parameterMap.get(paramKey);
//				boolean isString = value instanceof String;
//
//				if (value != null) {
//					if (isString) {
//						if (StringUtils.isNotEmpty((String) value)) {
//							query.add(this.getColumnName(paramKey,
//									buildSearchType), this.parameterMap
//									.get(paramKey), operationMap.get(paramKey));
//							this.parameterValues.add(value);
//						}
//					} else {
//						query.add(
//								this.getColumnName(paramKey, buildSearchType),
//								this.parameterMap.get(paramKey),
//								operationMap.get(paramKey));
//						this.parameterValues.add(value);
//					}
//
//				}
//			}
//			// for (SearchOrder order : orderBy) {
//			// if (StringUtils.isNotEmpty(order.getOrderName())) {
//			// SearchOrder realOrder = new
//			// SearchOrder(this.getColumnName(order.getOrderName(),
//			// buildSearchType),
//			// order.isAsc());
//			// search.getOrderByList().add(realOrder);
//			// }
//			// }
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return query;
//	}
//
//	private String getColumnName(String paramKey, String buildType)
//			throws Exception {
//		if (paramKey.indexOf(':') > 0) {
//			String[] keys = paramKey.split(":");
//			if (null != keys && keys.length == 2 && cmpClass != null) {
//				Field field = cmpClass.getDeclaredField(keys[0]);
//				if (null != field) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(keys[0]);
//					sb.append('.');
//					if (BUILDTYPE_NATIVE.equalsIgnoreCase(buildType)) {
//						sb.append(getColumnName(keys[1], buildType,
//								field.getClass()));
//					} else {
//						sb.append(keys[1]);
//					}
//					return sb.toString();
//				}
//			}
//		}
//		return getColumnName(paramKey, buildType, cmpClass);
//	}
//
//	private String getColumnName(String paramKey, String buildType,
//			Class<?> clazz) throws Exception {
//		String columnName = paramKey;
//		if (paramKey.endsWith("_start")) {
//			columnName = paramKey.replaceAll("_start", "");
//		}
//		if (paramKey.endsWith("_end")) {
//			columnName = paramKey.replaceAll("_end", "");
//		}
//		if (clazz != null && BUILDTYPE_NATIVE.equalsIgnoreCase(buildType)) {
//			Field field = clazz.getDeclaredField(columnName);
//			Column column = field.getAnnotation(Column.class);
//			if (column != null) {
//				columnName = column.name();
//			}
//		}
//		return columnName;
//	}
}
