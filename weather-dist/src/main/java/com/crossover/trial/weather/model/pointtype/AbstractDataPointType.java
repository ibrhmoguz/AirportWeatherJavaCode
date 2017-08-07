package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.AtmosphericInformation;
import com.crossover.trial.weather.model.DataPoint;

public abstract class AbstractDataPointType {

	public abstract boolean setAtmosphericInformation(AtmosphericInformation atmosphericInformation, DataPoint dataPoint);
}
