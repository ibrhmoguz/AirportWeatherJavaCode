package com.crossover.trial.weather.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A collected point, including some information about the range of collected
 * values
 *
 * @author code test administrator
 */
public class DataPoint {

	private double mean = 0.0;

	private int first = 0;

	private int median = 0;

	private int last = 0;

	private int count = 0;

	protected DataPoint(Builder builder) {
		this.mean = builder.mean;
		this.first = builder.first;
		this.median = builder.median;
		this.last = builder.last;
		this.count = builder.count;
	}

	public int getMedian() {
		return median;
	}

	public int getLast() {
		return last;
	}

	/** the mean of the observations */
	public double getMean() {
		return mean;
	}

	/** 1st quartile -- useful as a lower bound */
	public int getFirst() {
		return first;
	}

	/** the total number of measurements */
	public int getCount() {
		return count;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
	}

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

	public static class Builder {
		int first;
		int mean;
		int median;
		int last;
		int count;

		public Builder() {
		}

		public Builder withFirst(int first) {
			this.first = first;
			return this;
		}

		public Builder withMean(int mean) {
			this.mean = mean;
			return this;
		}

		public Builder withMedian(int median) {
			this.median = median;
			return this;
		}

		public Builder withCount(int count) {
			this.count = count;
			return this;
		}

		public Builder withLast(int last) {
			this.last = last;
			return this;
		}

		public DataPoint build() {
			return new DataPoint(this);
		}

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
