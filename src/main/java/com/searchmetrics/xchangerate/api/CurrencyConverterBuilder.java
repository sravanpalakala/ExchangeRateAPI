package com.searchmetrics.xchangerate.api;

import com.searchmetrics.xchangerate.endpoint.CurrencyLayerConverterEndpoint;
import com.searchmetrics.xchangerate.endpoint.CurrencyLayerEndpoint;
import com.searchmetrics.xchangerate.endpoint.CurrencyLayerHistoricalRateEndpoint;
import com.searchmetrics.xchangerate.storage.DiskStore;
import com.searchmetrics.xchangerate.storage.FileStore;
import com.searchmetrics.xchangerate.util.Strategy;


public class CurrencyConverterBuilder {
	private String _accessKey;
	private Strategy _strategy;
	private DiskStore diskStore;
	private String _currency;
	private String _startDate;
	private String _endDate;
	private String _from;
	private String _to;
	private String _amount;

	public CurrencyConverterBuilder() {
	}

	public CurrencyConverter buildConverter() {
		if (_strategy == null) {
			throw new IllegalArgumentException("No Strategy defined to create Currency converter");
		}  else if (_strategy.equals(Strategy.CURRENCY_LAYER_FILESTORE) && _accessKey != null) {
			diskStore = new FileStore("currencyLayer");
			return new CurrencyConverter(diskStore, new CurrencyLayerEndpoint(diskStore, _accessKey));
		}else if (_strategy.equals(Strategy.CURRENCY_LAYER_HISTORICAL_FILESTORE) && _accessKey != null) {
			diskStore = new FileStore("currencyLayer");
			return new CurrencyConverter(diskStore, new CurrencyLayerHistoricalRateEndpoint(diskStore, _accessKey,_currency,_startDate,_endDate));
		} else if(_strategy.equals(Strategy.CURRENCY_LAYER_CONVERTER_FILESTORE) && _accessKey != null) {
			diskStore = new FileStore("currencyLayer");
			return new CurrencyConverter(diskStore,new CurrencyLayerConverterEndpoint(diskStore, _accessKey,_from,_to,_amount));
		}
		throw new IllegalArgumentException("Strategy: " + _strategy + " requires accessKey for endpoint service, none provided!");
	}

	public CurrencyConverterBuilder strategy(Strategy _strategy) {
		this._strategy = _strategy;
		return this;
	}

	public CurrencyConverterBuilder accessKey(String _accessKey) {
		this._accessKey = _accessKey;
		return this;
	}
	
	public CurrencyConverterBuilder _startDate(String _startDate) {
		this._startDate = _startDate;
		return this;
	}
	public CurrencyConverterBuilder _endDate(String _endDate) {
		this._endDate = _endDate;
		return this;
	}
	
	public CurrencyConverterBuilder _currency(String _currency) {
		this._currency = _currency;
		return this;
	}
	public CurrencyConverterBuilder _from(String _from) {
		this._from = _from;
		return this;
	}
	
	public CurrencyConverterBuilder _to(String _to) {
		this._to = _to;
		return this;
	}
	
	public CurrencyConverterBuilder _amount(String _amount) {
		this._amount = _amount;
		return this;
	}
}
