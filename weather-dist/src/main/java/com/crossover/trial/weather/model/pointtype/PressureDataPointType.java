package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;

public class PressureDataPointType extends AbstractDataPointType {

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