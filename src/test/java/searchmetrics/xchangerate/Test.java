package searchmetrics.xchangerate;

import java.math.BigDecimal;

import org.json.JSONException;

import com.searchmetrics.xchangerate.api.CurrencyConverter;
import com.searchmetrics.xchangerate.api.CurrencyConverterBuilder;
import com.searchmetrics.xchangerate.api.CurrencyNotSupportedException;
import com.searchmetrics.xchangerate.endpoint.EndpointException;
import com.searchmetrics.xchangerate.service.ServiceException;
import com.searchmetrics.xchangerate.storage.StorageException;
import com.searchmetrics.xchangerate.util.Currency;
import com.searchmetrics.xchangerate.util.Strategy;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	/*	CurrencyConverter	converter = new CurrencyConverterBuilder()
				.strategy(Strategy.CURRENCY_LAYER_FILESTORE)
				.accessKey("7c1040d3fe11a80de0a10c92cd9fe97c")
				.buildConverter();
		converter.updateResource(Currency.USD, Currency.BIF);
		converter.setRefreshRateSeconds(86400);
		converter.setResourceFilepath("C:\\Users\\Lenovo\\AppData\\Local\\Temp\\");
		
		BigDecimal convertionAmnt =	converter.convertCurrency(new BigDecimal("1"), Currency.USD, Currency.BIF);
		System.out.println("After Conversion amount:"+convertionAmnt);*/
		
		
		
		//case : 2
		
		CurrencyConverter	historicalconverter = new CurrencyConverterBuilder()
				.strategy(Strategy.CURRENCY_LAYER_HISTORICAL_FILESTORE)
				.accessKey("7c1040d3fe11a80de0a10c92cd9fe97c")._currency("BIF")._startDate("2019-03-01")._endDate("2019-03-02")
				.buildConverter();
		historicalconverter.updateHistoricalRateResource(Currency.USD, Currency.BIF, "2019-03-01", "2019-03-02");
		historicalconverter.setRefreshRateSeconds(86400);
		historicalconverter.setResourceFilepath("C:\\Users\\Lenovo\\AppData\\Local\\Temp\\");
		
		
	} catch (CurrencyNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
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
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		

	}

}
