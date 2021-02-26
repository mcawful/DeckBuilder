/**
 * 
 */
package com.mcawful.deckbuilder.utils;

/**
 * A functional interface designed to be used in conjunction with DTO objects to
 * convert a DTO object to it's related POJO model object.
 * 
 * @param <E> the object type being passed in
 * @param <T> the object type being returned
 * @author Michael McAuliffe
 * 
 */
@FunctionalInterface
public interface DtoConverter<E, T> {

	/**
	 * Method to convert a DTO object to its related POJO model object.
	 * 
	 * @param e the DTO object to convert
	 * @return the related POJO model object
	 */
	public T convert(E e);

}
