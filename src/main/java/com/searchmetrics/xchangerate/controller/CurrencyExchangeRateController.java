package com.searchmetrics.xchangerate.controller;

import java.math.BigDecimal;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.searchmetrics.xchangerate.api.CurrencyConverter;
import com.searchmetrics.xchangerate.api.CurrencyConverterBuilder;
import com.searchmetrics.xchangerate.api.CurrencyNotSupportedException;
import com.searchmetrics.xchangerate.endpoint.EndpointException;
import com.searchmetrics.xchangerate.service.ServiceException;
import com.searchmetrics.xchangerate.storage.StorageException;
import com.searchmetrics.xchangerate.util.Currency;
import com.searchmetrics.xchangerate.util.Strategy;

@RestController
public class CurrencyExchangeRateController {
	
	@GetMapping("/api/getcurrencyexchangerate")
	@ResponseBody
	public String loadExchangeRates(@RequestParam String amount,@RequestParam String fromCurrency, @RequestParam String toCurrency)throws Exception{
	//public String loadExchangeRates(@RequestParam Map<String,String> allParams) throws Exception {
		BigDecimal convertionAmnt = new BigDecimal("0");
		CurrencyConverter converter = null;
		/*
		 * curl -X POST -F 'amount=1' -F 'fromCurrency=USD'   -F 'toCurrency=BIF' http://localhost:8080/api/getcurrencyexchangerate
  -----
          Parameters are {[amount=abc], [fromCurrency=USD]} , [toCurrency=BIF]}
		 */
		
		try {
	
			if (fromCurrency == null || toCurrency == null) {
				throw new Exception("from and to currency fields cannot be empty or null");
			}

			converter = new CurrencyConverterBuilder().strategy(Strategy.CURRENCY_LAYER_CONVERTER_FILESTORE)
					.accessKey("7c1040d3fe11a80de0a10c92cd9fe97c")._from(fromCurrency)._to(toCurrency)._amount(amount).buildConverter();
			converter.setRefreshRateSeconds(86400);
			converter.updateResource(Currency.BIF, Currency.USD);
		
			
			converter.setResourceFilepath("C:\\Users\\Lenovo\\AppData\\Local\\Temp\\");
			
			convertionAmnt = converter.convertCurrency(new BigDecimal(amount),  Currency.BIF,Currency.USD);
			System.out.println("After Conversion amount:" + convertionAmnt);
			
			converter.loadRates().toString();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CurrencyNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EndpointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    return converter.loadRates().toString();
	}
	
	
	@GetMapping("/api/getcurrencyhistoricalexchangerate")
	@ResponseBody
	public String loadHistoricalExchangeRates(@RequestParam String currency, @RequestParam String startDate,@RequestParam String endDate)throws Exception {
	
		/*
		 * curl -X POST -F 'amount=1' -F 'currency=BIF' -F 'start_date=2019-03-01' -F
		 * end_date=2019-03-02' http://localhost:8080/api/getcurrencyhistoricalexchangerate -----
		 * Parameters are {[amount=1],
		 * [currency=BIF],[start_date=2019-03-01],[end_date=2019-03-02] }
		 */
	
		try {

			if (startDate == null || endDate == null) {
				throw new Exception("startDate and endDate currency fields cannot be empty or null");
			}

			CurrencyConverter historicalconverter = new CurrencyConverterBuilder()
					.strategy(Strategy.CURRENCY_LAYER_HISTORICAL_FILESTORE)
					.accessKey("7c1040d3fe11a80de0a10c92cd9fe97c")._currency(currency)._startDate(startDate)
					._endDate(endDate).buildConverter();
			historicalconverter.updateHistoricalRateResource(Currency.USD,Currency.BIF, startDate, endDate);
			historicalconverter.setRefreshRateSeconds(86400);
			historicalconverter.setResourceFilepath("C:\\Users\\Lenovo\\AppData\\Local\\Temp\\");
		
			return historicalconverter.loadHistoricalRates().toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CurrencyNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EndpointException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed to get historical rates";
	}
}
