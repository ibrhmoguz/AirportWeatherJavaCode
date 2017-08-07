package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;

public class TemperatureDataPointType extends AbstractDataPointType {

	@Override
	public boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint) {
		if (dataPoint.getMean() >= -50 && dataPoint.getMean() < 100) {
			atmosphericInformation.setTemperature(dataPoint);
			atmosphericInformation.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
}
