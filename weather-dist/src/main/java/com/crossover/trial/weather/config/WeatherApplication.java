package com.crossover.trial.weather.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.crossover.trial.weather.RestWeatherCollectorEndpoint;
import com.crossover.trial.weather.RestWeatherQueryEndpoint;

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
        return classes;
    }
}
