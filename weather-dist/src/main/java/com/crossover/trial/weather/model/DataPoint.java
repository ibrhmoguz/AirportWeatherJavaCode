/*
 * 
 */
package com.crossover.trial.weather.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * A collected point, including some information about the range of collected
 * values.
 *
 * @author code test administrator
 */
public class DataPoint {

	/** The mean. */
	private double mean = 0.0;

	/** The first. */
	private int first = 0;

	/** The median. */
	private int median = 0;

	/** The last. */
	private int last = 0;

	/** The count. */
	private int count = 0;

	/**
	 * Instantiates a new data point.
	 *
	 * @param builder the builder
	 */
	protected DataPoint(Builder builder) {
		this.mean = builder.mean;
		this.first = builder.first;
		this.median = builder.median;
		this.last = builder.last;
		this.count = builder.count;
	}

	/**
	 * Gets the median.
	 *
	 * @return the median
	 */
	public int getMedian() {
		return median;
	}

	/**
	 * Gets the last.
	 *
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 *  the mean of the observations.
	 *
	 * @return the mean
	 */
	public double getMean() {
		return mean;
	}

	/**
	 *  1st quartile -- useful as a lower bound.
	 *
	 * @return the first
	 */
	public int getFirst() {
		return first;
	}

	/**
	 *  the total number of measurements.
	 *
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + first;
		result = prime * result + last;
		long temp;
		temp = Double.doubleToLongBits(mean);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + median;
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
		DataPoint other = (DataPoint) obj;
		if (count != other.count)
			return false;
		if (first != other.first)
			return false;
		if (last != other.last)
			return false;
		if (Double.doubleToLongBits(mean) != Double.doubleToLongBits(other.mean))
			return false;
		if (median != other.median)
			return false;
		return true;
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {
		
		/** The first. */
		int first;
		
		/** The mean. */
		int mean;
		
		/** The median. */
		int median;
		
		/** The last. */
		int last;
		
		/** The count. */
		int count;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
		}

		/**
		 * With first.
		 *
		 * @param first the first
		 * @return the builder
		 */
		public Builder withFirst(int first) {
			this.first = first;
			return this;
		}

		/**
		 * With mean.
		 *
		 * @param mean the mean
		 * @return the builder
		 */
		public Builder withMean(int mean) {
			this.mean = mean;
			return this;
		}

		/**
		 * With median.
		 *
		 * @param median the median
		 * @return the builder
		 */
		public Builder withMedian(int median) {
			this.median = median;
			return this;
		}

		/**
		 * With count.
		 *
		 * @param count the count
		 * @return the builder
		 */
		public Builder withCount(int count) {
			this.count = count;
			return this;
		}

		/**
		 * With last.
		 *
		 * @param last the last
		 * @return the builder
		 */
		public Builder withLast(int last) {
			this.last = last;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the data point
		 */
		public DataPoint build() {
			return new DataPoint(this);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + count;
			result = prime * result + first;
			result = prime * result + last;
			result = prime * result + mean;
			result = prime * result + median;
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
			Builder other = (Builder) obj;
			if (count != other.count)
				return false;
			if (first != other.first)
				return false;
			if (last != other.last)
				return false;
			if (mean != other.mean)
				return false;
			if (median != other.median)
				return false;
			return true;
		}
	}
}
