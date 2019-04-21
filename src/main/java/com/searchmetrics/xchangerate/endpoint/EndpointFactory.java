package com.searchmetrics.xchangerate.endpoint;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.json.JSONException;
import org.json.JSONObject;

import com.searchmetrics.xchangerate.api.CurrencyNotSupportedException;
import com.searchmetrics.xchangerate.caching.CachingXchangeRate;
import com.searchmetrics.xchangerate.service.HttpMethods;
import com.searchmetrics.xchangerate.service.HttpserviceImpl;
import com.searchmetrics.xchangerate.service.ServiceException;
import com.searchmetrics.xchangerate.storage.DiskStore;
import com.searchmetrics.xchangerate.util.Currency;


public abstract class EndpointFactory extends CachingXchangeRate implements ServiceEndpoint {
	// the intermediate currency
	public Currency baseCurrency;
	
	public String start_date;
	
	public String end_date;
	
	
	// used for executing requests to the (REST) API
	private HttpserviceImpl httpservice;
	protected JSONObject response;

	public EndpointFactory(DiskStore diskStore, Currency baseCurrency, String uri) {
		super(diskStore);
		this.baseCurrency = baseCurrency;
		httpservice = new HttpserviceImpl(uri);
	}

	/**
	 * sends the live request to currency layer API and saves the exchange rates from the response
	 *
	 * @throws JSONException
	 * @throws ServiceException
	 * @throws EndpointException
	 */
	public JSONObject sendLiveRequest() throws JSONException, ServiceException, EndpointException {
		response = httpservice.getResponse(HttpMethods.GET);
		if (checkResponse()) {
			return response;
		}
		return null;
	}

	/**
	 * helper method to convert to base currency e.g:USD
	 * Scenario : {other currency --> covert USD}
	 *
	 * @param moneyAmount money amount to convert
	 * @param fromCurrency currency to convert from
	 * @return double converted amount
	 * @throws JSONException
	 * @throws CurrencyNotSupportedException
	 */
	public BigDecimal convertToBaseCurrency(BigDecimal moneyAmount, Currency fromCurrency) throws JSONException, CurrencyNotSupportedException {
		return (moneyAmount.divide(getRate(fromCurrency), 2, RoundingMode.HALF_UP));
	}

	/**
	 * helper method to convert to base currency e.g:USD
	 * Scenario : {covert USD --> other currency}
	 *
	 * @param moneyAmount money amount to convert
	 * @param toCurrency currency to USD into
	 * @return double converted amount
	 * @throws JSONException
	 * @throws CurrencyNotSupportedException
	 */
	public BigDecimal convertFromBaseCurrency(BigDecimal moneyAmount, Currency toCurrency) throws JSONException, CurrencyNotSupportedException {
		int digitsBeforeDecimal = moneyAmount.toPlainString().split("\\.")[0].length();
		return getRate(toCurrency).multiply(moneyAmount, new MathContext(digitsBeforeDecimal + 2, RoundingMode.HALF_UP));
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
	public abstract boolean checkResponse() throws EndpointException, JSONException;

	/**
	 * retrieves rate of exchange price for the desiered currency
	 *
	 * @param currency
	 * @return BigDecimal exchange rate
	 * @throws JSONException
	 * @throws CurrencyNotSupportedException
	 */
	public abstract BigDecimal getRate(Currency currency) throws JSONException, CurrencyNotSupportedException;
	
	
	
	public abstract BigDecimal getHistoricalRate(Currency currencies,String startDate,String endDate) throws JSONException, CurrencyNotSupportedException;
}
