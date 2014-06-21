package com.thoughtworks.easterEggHunt.android.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import com.thoughtworks.easterEggHunt.CoordinateService;
import com.thoughtworks.easterEggHunt.R;
import com.thoughtworks.easterEggHunt.domain.Coordinate;

import java.util.List;


public class MainActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.hello);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, List<Coordinate>> {
        @Override
        protected List<Coordinate> doInBackground(String... strings) {
            CoordinateService coordinateService = new CoordinateService();
            return coordinateService.myCoordinates();
        }

        @Override
        protected void onPostExecute(List<Coordinate> coordinates) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Coordinate coordinate : coordinates) {
                String xCoord = String.valueOf(coordinate.getxCoord());
                String yCoord = String.valueOf(coordinate.getyCoord());
                stringBuilder.append("Lat: ")
                        .append(xCoord)
                        .append(", Lon: ")
                        .append(yCoord)
                        .append("\n");
            }

            textView.setText(stringBuilder.toString());
        }
    }

}
