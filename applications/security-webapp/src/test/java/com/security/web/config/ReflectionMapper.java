/**
 * 
 */
package com.security.web.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.security.client.bean.UserBean;
import com.security.domain.User;

/**
 * @author EFRAIN
 * @dateCreated 1 may. 2017 12:41:53
 */
public class ReflectionMapper {

	public static Set<Class<?>> getAllExtendedOrImplementedTypesRecursively(Class<?> clazz) {
	    List<Class<?>> res = new ArrayList<>();
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
	
	public static Object parseBetweenObjects(Object objectFrom,Object objectTo,Map<String, String> customMapping){
		try {
			Class<?> fromTempClass = objectFrom.getClass();
			Class<?> toTempClass = objectTo.getClass();
			Set<Class<?>> classesFrom = ReflectionMapper.getAllExtendedOrImplementedTypesRecursively(fromTempClass);
			Set<Class<?>> classesTo = ReflectionMapper.getAllExtendedOrImplementedTypesRecursively(toTempClass);
			
			for (Class<?> fromClass : classesFrom) {
			    for (Field fromField : fromClass.getDeclaredFields()) {
			    	
			    	if (customMapping.containsKey(fromField.getName())) {
			    		Field fieldValue = fromClass.getDeclaredField(fromField.getName());
					    fieldValue.setAccessible(true);
						Object value = fieldValue.get(objectFrom);
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
					}else{
						Field fieldValue = fromClass.getDeclaredField(fromField.getName());
					    fieldValue.setAccessible(true);
						Object value = fieldValue.get(objectFrom);
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
		} catch (Exception e) {
			// TODO: handle exception
		}
		return objectTo;
	}
	
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("valor1");
		user.setId(1L);
		user.setStatus("valor de nombre1");
		
		
		Map<String, String> map = new HashMap<>();
		map.put("status", "name");
		UserBean userBeanR = (UserBean)ReflectionMapper.parseObjectToNewClass(user,UserBean.class,map);
		System.out.println(userBeanR.getUserName());
		
		UserBean userBean = new UserBean();
		userBean.setUserName("valor2");
		userBean.setId(2L);
		userBean.setName("valor de nombre2");
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("name", "status");
		User userR = (User)ReflectionMapper.parseObjectToNewClass(userBean,User.class,map1);
		System.out.println(userR.getUserName());
		
		User user1 = (User)ReflectionMapper.parseBetweenObjects(userBean, user, map1);
		System.out.println(user1.getUserName());
		
		user.setUserName("valor3");
		user.setId(3L);
		user.setStatus("valor de nombre3");
		UserBean userBean1 = (UserBean)ReflectionMapper.parseBetweenObjects(user, userBean, map);
		System.out.println(userBean1.getUserName());
	}

}
