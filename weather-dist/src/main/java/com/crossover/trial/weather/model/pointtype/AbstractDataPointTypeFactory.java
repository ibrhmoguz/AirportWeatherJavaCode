/*
 * 
 */
package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.DataPointType;


/**
 * A factory for creating AbstractDataPointType objects.
 */
public class AbstractDataPointTypeFactory {

	/**
	 * Instantiates a new abstract data point type factory.
	 */
	private AbstractDataPointTypeFactory() {
	}

	/**
	 * Gets the data point type.
	 *
	 * @param pointType the point type
	 * @return the data point type
	 */
	public static AbstractDataPointType getDataPointType(String pointType) {

		AbstractDataPointType dataType = null;
		DataPointType dataPointTypeEnum = DataPointType.valueOf(pointType.toUpperCase());

		switch (dataPointTypeEnum) {
		case WIND:
			dataType = new WindDataPointType();
			break;
		case CLOUDCOVER:
			dataType = new CloudCoverDataPointType();
			break;
		case HUMIDTY:
			dataType = new HumidityDataPointType();
			break;
		case PRECIPITATION:
			dataType = new PrecipitationDataPointType();
			break;
		case PRESSURE:
			dataType = new PressureDataPointType();
			break;
		case TEMPERATURE:
			dataType = new TemperatureDataPointType();
			break;
		default:
			break;
		}
		return dataType;
	}
}
