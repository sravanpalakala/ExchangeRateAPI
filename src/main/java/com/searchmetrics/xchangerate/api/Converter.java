package com.searchmetrics.xchangerate.api;

import java.math.BigDecimal;

import org.json.JSONException;

import com.searchmetrics.xchangerate.endpoint.EndpointException;
import com.searchmetrics.xchangerate.service.ServiceException;
import com.searchmetrics.xchangerate.storage.StorageException;
import com.searchmetrics.xchangerate.util.Currency;

public interface Converter {

	public BigDecimal convertCurrency(BigDecimal moneyAmount, Currency fromCurrency, Currency toCurrency) throws CurrencyNotSupportedException, JSONException, StorageException, EndpointException, ServiceException;
}
