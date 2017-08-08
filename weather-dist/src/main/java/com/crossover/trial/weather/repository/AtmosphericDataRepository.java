/*
 * 
 */
package com.crossover.trial.weather.repository;

import java.util.Map;

import com.crossover.trial.weather.model.AtmosphericInformation;


/**
 * The Interface AtmosphericDataRepository.
 */
public interface AtmosphericDataRepository {

	/**
	 * Gets the atmospheric information.
	 *
	 * @return the atmospheric information
	 */
	Map<Integer, AtmosphericInformation> getAtmosphericInformation();

	/**
	 * Gets the atmospheric information.
	 *
	 * @param idx the idx
	 * @return the atmospheric information
	 */
	AtmosphericInformation getAtmosphericInformation(int idx);

	/**
	 * Adds the atmospheric information.
	 *
	 * @param idx the idx
	 * @param atmosphericInformation the atmospheric information
	 */
	void addAtmosphericInformation(Integer idx, AtmosphericInformation atmosphericInformation);
}
