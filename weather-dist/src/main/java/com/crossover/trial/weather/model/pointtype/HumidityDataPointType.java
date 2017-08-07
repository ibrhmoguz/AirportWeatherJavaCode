package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;

public class HumidityDataPointType extends AbstractDataPointType {

	@Override
	public boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint) {
		if (dataPoint.getMean() >= 0 && dataPoint.getMean() < 100) {
			atmosphericInformation.setHumidity(dataPoint);
			atmosphericInformation.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
}