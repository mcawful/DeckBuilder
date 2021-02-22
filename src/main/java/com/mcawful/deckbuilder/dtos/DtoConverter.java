/**
 * 
 */
package com.mcawful.deckbuilder.dtos;

/**
 * A functional interface designed to be used in conjunction with DTO objects to
 * convert a DTO object to it's related POJO model object.
 * 
 * @param <T> The expected object type being returned
 * @author Michael McAuliffe
 * 
 */
@FunctionalInterface
public interface DtoConverter<T> {

	/**
	 * Method to convert a DTO object to its related POJO model object.
	 * 
	 * @return The related POJO model object
	 */
	public T dtoToPojo();
}
