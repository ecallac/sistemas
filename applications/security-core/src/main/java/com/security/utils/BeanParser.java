/**
 * 
 */
package com.security.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author EFRAIN
 * @dateCreated 1 may. 2017 12:20:49
 */
public class BeanParser {
	
	protected static Set<Class<?>> getAllExtendedOrImplementedTypesRecursively(Class<?> clazz) {
	    List<Class<?>> res = new ArrayList<Class<?>>();
	    do {
	        res.add(clazz);
	        Class<?>[] interfaces = clazz.getInterfaces();
	        if (interfaces.length > 0) {
	            res.addAll(Arrays.asList(interfaces));
	            for (Class<?> interfaze : interfaces) {
	                res.addAll(getAllExtendedOrImplementedTypesRecursively(interfaze));
	            }
	        }
	        Class<?> superClass = clazz.getSuperclass();
	        if (superClass == null) {
	            break;
	        } 
	        clazz = superClass;
	    } while (!"java.lang.Object".equals(clazz.getCanonicalName()));
	    return new HashSet<Class<?>>(res);
	}
	
	/**
	 * 
	 * 
	 * @param objectFrom
	 * @param objectTo
	 * @param customMapping is Map<String, String> 
	 * 		e.g. map.put("fieldA", "fieldB");
	 * 			this is objectFrom.fieldA --> objectTo.fieldB
	 * @return
	 */
	public static Object parseBetweenObjects(Object objectFrom,Object objectTo,Map<String, String> customMapping){
		try {
			Class<?> fromTempClass = objectFrom.getClass();
			Class<?> toTempClass = objectTo.getClass();
			Set<Class<?>> classesFrom = getAllExtendedOrImplementedTypesRecursively(fromTempClass);
			Set<Class<?>> classesTo = getAllExtendedOrImplementedTypesRecursively(toTempClass);
			
			for (Class<?> fromClass : classesFrom) {
			    for (Field fromField : fromClass.getDeclaredFields()) {
			    	
			    	if (customMapping!=null && customMapping.containsKey(fromField.getName())) {
			    		Field fieldValue = fromClass.getDeclaredField(fromField.getName());
					    fieldValue.setAccessible(true);
						Object value = fieldValue.get(objectFrom);
						if (value != null) {
							try {
								for (Class<?> toClazz : classesTo) {
									try {
										Field toField = toClazz.getDeclaredField(customMapping.get(fromField.getName()));
										toField.setAccessible(true);
										toField.set(objectTo, value);
									} catch (Exception e) {}
								}
							} catch (Exception e) {
							}
						}
					}else{
						Field fieldValue = fromClass.getDeclaredField(fromField.getName());
					    fieldValue.setAccessible(true);
						Object value = fieldValue.get(objectFrom);
						if (value != null) {
							try {
								for (Class<?> toClazz : classesTo) {
									try {
										Field toField = toClazz.getDeclaredField(fromField.getName());
										toField.setAccessible(true);
										toField.set(objectTo, value);
									} catch (Exception e) {}
								}
							} catch (Exception e) {
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objectTo;
	}
	
	/**
	 * 
	 * 
	 * @param objectFrom
	 * @param toClass
	 * @param customMapping is Map<String, String> 
	 * 		e.g. map.put("fieldA", "fieldB");
	 * 			this is objectFrom.fieldA --> toClass.fieldB
	 * @return
	 */
	public static Object parseObjectToNewClass(Object objectFrom,Class<?> toClass,Map<String, String> customMapping){
		try {
			Object toClassInstanced = toClass.newInstance();
			parseBetweenObjects(objectFrom, toClassInstanced, customMapping);
			
			return toClassInstanced;
		} catch (Exception e) {
			e.printStackTrace();
		}   
		return null;
	}
	
}
