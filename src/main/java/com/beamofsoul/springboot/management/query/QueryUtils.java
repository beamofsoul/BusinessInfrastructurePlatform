package com.beamofsoul.springboot.management.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueryUtils {
	
	public static final Logger LOGGER = LoggerFactory
			.getLogger(QueryUtils.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public QueryUtils() {}

	public <D> List<D> executeDynamicQuerySql(String sql,
			QueryCriteria criteria, Class<? extends D> targetClass) {
		Map<String, Object> params = new HashMap<String, Object>();
		String completeSql = SQLBuilder.buildDynamicSql(sql, criteria, params);
		return executeQuerySql(completeSql, params, targetClass);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> executeQuerySql(String sql,
			Map<String, Object> params, Class<? extends T> targetClass) {

		LOGGER.debug("Execute native query sql [{}] with parameters [{}] for target [{}]",
				sql, params, targetClass);
		
		Query query = getEntityManager().createNativeQuery(sql);
//		if (DbColumnMapper.isNamedMapping(targetClass)) {
			query.unwrap(SQLQuery.class).setResultTransformer(
					Transformers.ALIAS_TO_ENTITY_MAP);
//		}
		for (Entry<String, Object> entry : params.entrySet())
			query.setParameter(entry.getKey(), entry.getValue());
		
		return query.getResultList();
//		return DbColumnMapper.resultMapping(query.getResultList(), targetClass);
	}
}
