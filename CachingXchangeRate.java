package com.searchmetrics.xchangerate.caching;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import com.searchmetrics.xchangerate.api.CurrencyNotSupportedException;
import com.searchmetrics.xchangerate.endpoint.EndpointException;
import com.searchmetrics.xchangerate.storage.DiskStore;
import com.searchmetrics.xchangerate.storage.StorageException;
import com.searchmetrics.xchangerate.util.Currency;


public abstract class CachingXchangeRate {

	public int refreshRateSeconds = 86400;
	public JSONObject exchangeRates = null;
	public JSONObject exchangeHistoricalRates = null;
	private DiskStore diskStore;
 
	public CachingXchangeRate(DiskStore diskStore) {
		this.diskStore = diskStore;
	}

	public void setExchangeRates(JSONObject exchangeRates) {
		this.exchangeRates=exchangeRates;
	}

	public void setExchangeHistoricalRates(JSONObject exchangeHistoricalRates) {
		this.exchangeHistoricalRates=exchangeHistoricalRates;
	}
	
	/**
	 * checks if the rates have expired judging from the timestamp of
	 * the stored exchange rate resource file
	 *
	 * @return boolean truth value
	 * @throws EndpointException
	 * @throws CurrencyNotSupportedException
	 * @throws StorageException
	 */
	public boolean checkRatesUsable(Currency currency) throws JSONException, CurrencyNotSupportedException, StorageException {
		if (!diskStore.resourceExists()) {
			return false;
		} else if (exchangeRates == null) {
			setExchangeRates(diskStore.loadRates());
		}
		// calculate the difference in timestamp and return false if not expired
		long old = getTimestamp(currency);
		long now = new DateTime().getMillis();
		if (Math.abs((old - now) / 1000) < (refreshRateSeconds)) {
			return true;
		}
		// return true if the timestamp has expired
		return false;
	}

	
	/**
	 * checks if the historical rates have expired judging from the timestamp of
	 * the stored exchange rate resource file
	 *
	 * @return boolean truth value
	 * @throws EndpointException
	 * @throws CurrencyNotSupportedException
	 * @throws StorageException
	 */
	public boolean checkHistoricalRatesUsable(Currency currency) throws JSONException, CurrencyNotSupportedException, StorageException {
		if (!diskStore.resourceHistoricalRateFileExists()) {
			return false;
		} else if (exchangeHistoricalRates == null) {
			setExchangeHistoricalRates(diskStore.loadHistoricalRates());
		}
		// calculate the difference in timestamp and return false if not expired
		long old = getTimestamp(currency);
		long now = new DateTime().getMillis();
		if (Math.abs((old - now) / 1000) < (refreshRateSeconds)) {
			return true;
		}
		// return true if the timestamp has expired
		return false;
	}

	/**
	 * get the timestamp of associated exchange rate
	 *
	 * @param currency
	 * @return timestamp
	 * @throws EndpointException
	 * @throws CurrencyNotSupportedException
	 */
	public abstract long getTimestamp(Currency currency) throws JSONException, CurrencyNotSupportedException;
}
