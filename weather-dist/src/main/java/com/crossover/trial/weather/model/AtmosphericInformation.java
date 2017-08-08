/*
 * 
 */
package com.crossover.trial.weather.model;


/**
 * encapsulates sensor information for a particular location.
 */
public class AtmosphericInformation {

    /**  temperature in degrees celsius. */
    private DataPoint temperature;

    /**  wind speed in km/h. */
    private DataPoint wind;

    /**  humidity in percent. */
    private DataPoint humidity;

    /**  precipitation in cm. */
    private DataPoint precipitation;

    /**  pressure in mmHg. */
    private DataPoint pressure;

    /**  cloud cover percent from 0 - 100 (integer). */
    private DataPoint cloudCover;

    /**  the last time this data was updated, in milliseconds since UTC epoch. */
    private long lastUpdateTime;

    /**
     * Instantiates a new atmospheric information.
     */
    public AtmosphericInformation() {

    }

    /**
     * Instantiates a new atmospheric information.
     *
     * @param temperature the temperature
     * @param wind the wind
     * @param humidity the humidity
     * @param percipitation the percipitation
     * @param pressure the pressure
     * @param cloudCover the cloud cover
     */
    protected AtmosphericInformation(DataPoint temperature, DataPoint wind, DataPoint humidity, DataPoint percipitation, DataPoint pressure, DataPoint cloudCover) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.precipitation = percipitation;
        this.pressure = pressure;
        this.cloudCover = cloudCover;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cloudCover == null) ? 0 : cloudCover.hashCode());
		result = prime * result + ((humidity == null) ? 0 : humidity.hashCode());
		result = prime * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
		result = prime * result + ((precipitation == null) ? 0 : precipitation.hashCode());
		result = prime * result + ((pressure == null) ? 0 : pressure.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
		result = prime * result + ((wind == null) ? 0 : wind.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtmosphericInformation other = (AtmosphericInformation) obj;
		if (cloudCover == null) {
			if (other.cloudCover != null)
				return false;
		} else if (!cloudCover.equals(other.cloudCover))
			return false;
		if (humidity == null) {
			if (other.humidity != null)
				return false;
		} else if (!humidity.equals(other.humidity))
			return false;
		if (lastUpdateTime != other.lastUpdateTime)
			return false;
		if (precipitation == null) {
			if (other.precipitation != null)
				return false;
		} else if (!precipitation.equals(other.precipitation))
			return false;
		if (pressure == null) {
			if (other.pressure != null)
				return false;
		} else if (!pressure.equals(other.pressure))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		if (wind == null) {
			if (other.wind != null)
				return false;
		} else if (!wind.equals(other.wind))
			return false;
		return true;
	}

	/**
	 * Gets the temperature.
	 *
	 * @return the temperature
	 */
	public DataPoint getTemperature() {
        return temperature;
    }
    
    /**
     * Sets the temperature.
     *
     * @param temperature the new temperature
     */
    public void setTemperature(DataPoint temperature) {
        this.temperature = temperature;
    }
    
    /**
     * Gets the wind.
     *
     * @return the wind
     */
    public DataPoint getWind() {
        return wind;
    }
    
    /**
     * Sets the wind.
     *
     * @param wind the new wind
     */
    public void setWind(DataPoint wind) {
        this.wind = wind;
    }
    
    /**
     * Gets the humidity.
     *
     * @return the humidity
     */
    public DataPoint getHumidity() {
        return humidity;
    }
    
    /**
     * Sets the humidity.
     *
     * @param humidity the new humidity
     */
    public void setHumidity(DataPoint humidity) {
        this.humidity = humidity;
    }
    
    /**
     * Gets the precipitation.
     *
     * @return the precipitation
     */
    public DataPoint getPrecipitation() {
        return precipitation;
    }
    
    /**
     * Sets the precipitation.
     *
     * @param precipitation the new precipitation
     */
    public void setPrecipitation(DataPoint precipitation) {
        this.precipitation = precipitation;
    }
    
    /**
     * Gets the pressure.
     *
     * @return the pressure
     */
    public DataPoint getPressure() {
        return pressure;
    }
    
    /**
     * Sets the pressure.
     *
     * @param pressure the new pressure
     */
    public void setPressure(DataPoint pressure) {
        this.pressure = pressure;
    }
    
    /**
     * Gets the cloud cover.
     *
     * @return the cloud cover
     */
    public DataPoint getCloudCover() {
        return cloudCover;
    }
    
    /**
     * Sets the cloud cover.
     *
     * @param cloudCover the new cloud cover
     */
    public void setCloudCover(DataPoint cloudCover) {
        this.cloudCover = cloudCover;
    }
    
    /**
     * Gets the last update time.
     *
     * @return the last update time
     */
    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
    /**
     * Sets the last update time.
     *
     * @param lastUpdateTime the new last update time
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
