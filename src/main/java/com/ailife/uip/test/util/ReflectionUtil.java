package com.ailife.uip.test.util;

import com.ailife.uip.test.db.entity.Inter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenmm6 on 2014/11/3.
 */
public class ReflectionUtil {

	private final static List<Class> classes = Arrays.asList(new Class[]{String.class, Integer.class, Short.class, Long.class, Character.class, Byte.class, Float.class, Double.class, Boolean.class});

	public static boolean isBeanField(Field field) {
		int modifiers = field.getModifiers();
		return (Modifier.isPrivate(modifiers) && !Modifier.isFinal(modifiers) && !Modifier.isStatic(modifiers));
	}

	public static boolean isPrimitiveOrString(Field field) {
		return field.getType().isPrimitive() || classes.contains(field.getType());
	}

}
