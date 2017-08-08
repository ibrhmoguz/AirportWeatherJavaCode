/*
 * 
 */
package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;


/**
 * The Class PressureDataPointType.
 */
public class PressureDataPointType extends AbstractDataPointType {

	/* (non-Javadoc)
	 * @see com.crossover.trial.weather.model.pointtype.AbstractDataPointType#setAtmosphericInformation(com.crossover.trial.weather.model.AtmosphericInformation, com.crossover.trial.weather.model.DataPoint)
	 */
	@Override
	public boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint) {
		if (dataPoint.getMean() >= 650 && dataPoint.getMean() < 800) {
			atmosphericInformation.setPressure(dataPoint);
			atmosphericInformation.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
}