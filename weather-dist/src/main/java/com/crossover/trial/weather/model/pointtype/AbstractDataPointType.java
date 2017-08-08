/*
 * 
 */
package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;


/**
 * The Class AbstractDataPointType.
 */
public abstract class AbstractDataPointType {

	/**
	 * Sets the atmospheric information.
	 *
	 * @param atmosphericInformation the atmospheric information
	 * @param dataPoint the data point
	 * @return true, if successful
	 */
	public abstract boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint);
}
