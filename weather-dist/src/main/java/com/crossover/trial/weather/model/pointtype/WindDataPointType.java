package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;

public class WindDataPointType extends AbstractDataPointType {

	@Override
	public boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint) {
		if (dataPoint.getMean() >= 0) {
			atmosphericInformation.setWind(dataPoint);
			atmosphericInformation.setLastUpdateTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}
}
