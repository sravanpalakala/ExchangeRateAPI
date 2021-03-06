package com.searchmetrics.xchangerate.endpoint;

import java.math.BigDecimal;

import org.json.JSONException;

import com.searchmetrics.xchangerate.api.CurrencyNotSupportedException;
import com.searchmetrics.xchangerate.storage.DiskStore;
import com.searchmetrics.xchangerate.util.Currency;
/*
 * 
http://apilayer.net/api/timeframe
    ? access_key = 7c1040d3fe11a80de0a10c92cd9fe97c
    & currencies = BIF
    & start_date = 2019-03-01
    & end_date = 2019-04-01
 */
public class CurrencyLayerHistoricalRateEndpoint extends EndpointFactory {

	public static final String BASE_URL = "http://apilayer.net/api/";
	public static final String ENDPOINT = "timeframe";
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CurrencyLayerHistoricalRateEndpoint.class.getName());

	public CurrencyLayerHistoricalRateEndpoint(DiskStore diskStore, String accessKey,String currencies,String startDate,String endDate) {
				super(diskStore, Currency.USD, BASE_URL + ENDPOINT + "?access_key=" + accessKey+"& currencies ="+currencies+"& start_date ="+startDate+"& end_date ="+endDate);
	}



	/**
	 * Checks if the response from the web service
	 * is proper and can be cached/stored for offline
	 * use of currency conversion
	 *
	 * @return boolean truth value
	 * @throws EndpointException
	 * @throws JSONException
	 */
	public boolean checkResponse() throws EndpointException, JSONException {
		if (response.get("success").toString().equalsIgnoreCase("false")) {
			throw new EndpointException("Currency Layer request did not succeed, info: " + response.getJSONObject("error").get("info"));
		}
		return true;
	}

	/**
	 * Helper to retrieves the timestamp from the stored exchange rate
	 *
	 * @param currency (not used in this case)
	 * @return long timestamp in linux format
	 * @throws EndpointException
	 */
	public long getTimestamp(Currency currency) throws JSONException {
		return Long.parseLong(exchangeHistoricalRates.get("timestamp").toString(), 10) * 1000;
	}

	
	public BigDecimal getHistoricalRate(Currency currency, String startDate, String endDate)
			throws JSONException, CurrencyNotSupportedException {
		// TODO Auto-generated method stub
		//return new BigDecimal(exchangeHistoricalRates.getJSONObject("quotes").getDouble("USD" + currency).getString("start_date"+startDate).getString("end_date"+endDate));
		
		return new BigDecimal(exchangeHistoricalRates.getJSONObject("quotes").getDouble("USD" + currency));
	}



	@Override
	public BigDecimal getRate(Currency currency) throws JSONException, CurrencyNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}


}
