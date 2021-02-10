

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.os.AsyncTask;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;





public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray  dailyChanges= new JSONArray()

               /* JSONObject state= jsonObject.getJSONObject("Madhya Pradesh");
                JSONObject districtData= state.getJSONObject("districtData");
                JSONObject districtSDL= districtData.getJSONObject("Shahdol");
                Log.i("Sdl",districtSDL.getString("confirmed"));*/

                /*String weatherInfo = jsonObject.getString("Madhya Pradesh");

                Log.i("Weather content", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i=0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    Log.i("main",jsonPart.getString("main"));
                    Log.i("description",jsonPart.getString("description"));


                }*/



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("https://api.covid19india.org/states_daily.json");
    }
