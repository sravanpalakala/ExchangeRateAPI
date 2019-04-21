package com.searchmetrics.xchangerate.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;


public class FileStore extends DiskStore {
	protected String ratesFilename;
	protected String historicalRatesFilename;

	public FileStore(String filenameAppender) {
		this.ratesFilename = System.getProperty("file.separator") + filenameAppender + "XchangeRates.json";
		this.historicalRatesFilename = System.getProperty("file.separator") + filenameAppender + "HistoricalXchangeRates.json";
	}

	/**
	 * saves the exchange rates in a stored resource file
	 *
	 * @throws StorageException
	 */
	public void saveRates(JSONObject exchangeRates) throws StorageException {
		if (exchangeRates == null) {
			throw new StorageException("Cannot save null exchangeRates!");
		}
		try {
			FileWriter file = new FileWriter(resourceFilepath + ratesFilename);
			file.write(exchangeRates.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}
	
	/**
	 * saves the exchange historical rates in a stored resource file
	 *
	 * @throws StorageException
	 */
	public void saveHistoricalRates(JSONObject exchangeHistoricalRates) throws StorageException {
		if (exchangeHistoricalRates == null) {
			throw new StorageException("Cannot save null exchangeHistoricalRates!");
		}
		try {
			FileWriter file = new FileWriter(resourceFilepath + historicalRatesFilename);
			file.write(exchangeHistoricalRates.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}

	/**
	 * Parses the exchange rates from the stored resource file
	 * and stores them as a JSONObject
	 *
	 * @throws StorageException
	 */
	public JSONObject loadRates() throws StorageException {
		// parse the JSON string from the resource file
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(resourceFilepath + ratesFilename));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			throw new StorageException(e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// covert the parsed string to a JSON object
		try {
			return new JSONObject(jsonData);
		} catch (JSONException e) {
			throw new StorageException(e);
		}
	}

	
	/**
	 * Parses the exchange historical rates from the stored resource file
	 * and stores them as a JSONObject
	 *
	 * @throws StorageException
	 */
	public JSONObject loadHistoricalRates() throws StorageException {
		// parse the JSON string from the resource file
		String jsonData = "";
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(resourceFilepath + historicalRatesFilename));
			while ((line = br.readLine()) != null) {
				jsonData += line + "\n";
			}
		} catch (IOException e) {
			throw new StorageException(e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// covert the parsed string to a JSON object
		try {
			return new JSONObject(jsonData);
		} catch (JSONException e) {
			throw new StorageException(e);
		}
	}
	/**
	 * check if this resource file exists
	 *
	 * @return boolean truth value
	 */
	public boolean resourceExists() {
		File f = new File(resourceFilepath + ratesFilename);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if this resource file exists
	 *
	 * @return boolean truth value
	 */
	public boolean resourceHistoricalRateFileExists() {
		File f = new File(resourceFilepath + historicalRatesFilename);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}
}
