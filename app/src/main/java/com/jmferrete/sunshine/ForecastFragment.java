package com.jmferrete.sunshine;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jmferrete.sunshine.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForecastFragment extends Fragment {

	public ForecastFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		String[] data = {
				"Today - Sunny - 88/63",
				"Tomorrow - Foggy - 70/46",
				"Weds - Cloudy - 72/63",
				"Thurs - Rainy - 64/51",
				"Fry - Foggy - 70/46",
				"Sat - Sunny - 76/68"
		};

		List<String> weekForecast = new ArrayList<>(Arrays.asList(data));

		ArrayAdapter<String> forecastAdapter = new ArrayAdapter<>(
				getActivity(),
				R.layout.list_item_forecast,
				R.id.list_item_forecast_textview,
				weekForecast
		);

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);

		ListView myView = (ListView) rootView.findViewById(R.id.listview_forecast);
		myView.setAdapter(forecastAdapter);

		return myView;
	}


	class FetchWeatherClass extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... args) {
			// These two need to be declared outside the try/catch
			// so that they can be closed in the finally block.
			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;

			// Will contain the raw JSON response as a string.
			String forecastJsonStr = null;

			try {
				// Construct the URL for the OpenWeatherMap query
				// Possible parameters are available at OWM's forecast API page, at
				// http://openweathermap.org/API#forecast
				URL url = new URL(args[0]);

				// Create the request to OpenWeatherMap, and open the connection
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.connect();

				// Read the input stream into a String
				InputStream inputStream = urlConnection.getInputStream();
				StringBuffer buffer = new StringBuffer();
				if (inputStream == null) {
					// Nothing to do.
					forecastJsonStr = null;
				}
				reader = new BufferedReader(new InputStreamReader(inputStream));

				String line;
				while ((line = reader.readLine()) != null) {
					// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
					// But it does make debugging a *lot* easier if you print out the completed
					// buffer for debugging.
					buffer.append(line + "\n");
				}

				if (buffer.length() == 0) {
					// Stream was empty.  No point in parsing.
					forecastJsonStr = null;
				}
				forecastJsonStr = buffer.toString();
			} catch (IOException e) {
				Log.e("ForecastFragment", "Error ", e);
				// If the code didn't successfully get the weather data, there's no point in attempting
				// to parse it.
				forecastJsonStr = null;
			} finally{
				if (urlConnection != null) {
					urlConnection.disconnect();
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (final IOException e) {
						Log.e("ForecastFragment", "Error closing stream", e);
					}
				}
			}

			return forecastJsonStr;
		}
	}
}
