package com.entities.structure.utils;

import java.util.HashSet;

public class IteratorEntity<T> extends HashSet<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public T get(T source) {
		if (this.contains(source)) {
			for (T obj : this) {
				if (obj.equals(source))
					return obj;
			}
		}

		return null;
	}
}
