package com.crossover.trial.weather.model.pointtype;

import com.crossover.trial.weather.model.DataPointType;

public class AbstractDataPointTypeFactory {

	private AbstractDataPointTypeFactory() {
	}

	public static AbstractDataPointType getDataPointType(String pointType) {

		AbstractDataPointType dataType = null;
		DataPointType dataPointTypeEnum = DataPointType.valueOf(pointType);

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
