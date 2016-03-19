package com.jmferrete.sunshine;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] weekForecast = {
				"Today - Sunny - 88/63",
				"Tomorrow - Foggy - 70/46",
				"Weds - Cloudy - 72/63",
				"Thurs - Rainy - 64/51",
				"Fry - Foggy - 70/46",
				"Sat - Sunny - 76/68"
		};

		ArrayAdapter<String> mForecastAdapter = new ArrayAdapter<>(
				this,
				R.layout.list_item_forecast,
				R.id.list_item_forecast_textview,
				weekForecast
		);

		setContentView(R.layout.activity_main);

		ListView myView = (ListView) findViewById(R.id.listview_forecast);
		myView.setAdapter(mForecastAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
