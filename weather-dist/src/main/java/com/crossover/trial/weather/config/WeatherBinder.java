package com.crossover.trial.weather.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.crossover.trial.weather.repository.AirportDataRepository;
import com.crossover.trial.weather.repository.AirportDataRepositoryImpl;
import com.crossover.trial.weather.repository.AtmosphericDataRepository;
import com.crossover.trial.weather.repository.AtmosphericDataRepositoryImpl;
import com.crossover.trial.weather.repository.FrequencyDataRepository;
import com.crossover.trial.weather.repository.FrequencyDataRepositoryImpl;
import com.crossover.trial.weather.service.WeatherCollectService;
import com.crossover.trial.weather.service.WeatherCollectServiceImpl;
import com.crossover.trial.weather.service.WeatherQueryService;
import com.crossover.trial.weather.service.WeatherQueryServiceImpl;


/**
 * The Class WeatherBinder binds interface and its implementation class.
 *
 * @author Ibrahim OGUZ.
 */
public class WeatherBinder extends AbstractBinder {
    
    /* (non-Javadoc)
     * @see org.glassfish.hk2.utilities.binding.AbstractBinder#configure()
     */
    @Override
    protected void configure() {
        bind(WeatherQueryServiceImpl.class).to(WeatherQueryService.class);
        bind(WeatherCollectServiceImpl.class).to(WeatherCollectService.class);
        bind(AirportDataRepositoryImpl.class).to(AirportDataRepository.class);
        bind(AtmosphericDataRepositoryImpl.class).to(AtmosphericDataRepository.class);
        bind(FrequencyDataRepositoryImpl.class).to(FrequencyDataRepository.class);
    }
}
