package com.crossover.trial.weather.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.crossover.trial.weather.exception.AirportAlreadyExistsHandler;
import com.crossover.trial.weather.exception.AirportNotFoundHandler;
import com.crossover.trial.weather.exception.AtmosphericInformationNotFoundHandler;
import com.crossover.trial.weather.exception.RadiusFrequencyNotFoundHandler;
import com.crossover.trial.weather.exception.RequestFrequencyNotFoundHandler;
import com.crossover.trial.weather.exception.WeatherExceptionHandler;
import com.crossover.trial.weather.rest.RestWeatherCollectorEndpoint;
import com.crossover.trial.weather.rest.RestWeatherQueryEndpoint;


/**
 * The Class WeatherApplication keeps all resource classes.
 * 
 * @author Ibrahim OGUZ.
 */
public class WeatherApplication extends Application{
	
	/* (non-Javadoc)
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(RestWeatherQueryEndpoint.class);
        classes.add(RestWeatherCollectorEndpoint.class);
        classes.add(AirportAlreadyExistsHandler.class);
        classes.add(AirportNotFoundHandler.class);
        classes.add(AtmosphericInformationNotFoundHandler.class);
        classes.add(RadiusFrequencyNotFoundHandler.class);
        classes.add(RequestFrequencyNotFoundHandler.class);
        classes.add(WeatherExceptionHandler.class);
        return classes;
    }
}
