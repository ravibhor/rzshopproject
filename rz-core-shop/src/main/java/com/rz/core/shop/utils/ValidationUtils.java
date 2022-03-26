package com.rz.core.shop.utils;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class ValidationUtils {

	private ValidationUtils() {
	}

	/**
	 * This method returns true if the collection is null or is empty.
	 * 
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * This method returns true if the map is null or is empty.
	 * 
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * This method returns true if the object is null.
	 * 
	 * @param object
	 * @return true | false
	 */
	public static boolean isEmpty(Object object) {
		return object == null;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 * 
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 * 
	 * @param string
	 * @return true | false
	 */
	public static boolean isEmpty(String string) {
		return (string == null || string.isEmpty());
	}

	/**
	 * This method checks if the given input string can be converted to UUID or not.
	 * 
	 * @param uuidString
	 * @return
	 */
	public static boolean isValidUUID(String uuidString) {
		// UUID string length should be 36 character for a valid UUID.
		if (isEmpty(uuidString) || uuidString.length() != 36) {
			return false;
		}
		boolean isValid = true;
		try {
			UUID.fromString(uuidString);
		} catch (IllegalArgumentException e) {
			isValid = false;
		}
		return isValid;
	}
}