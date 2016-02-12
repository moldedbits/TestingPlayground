package com.moldedbits.testingplayground;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            PlaceholderFragment f = new PlaceholderFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(), "fragment")
                    .commit();
        }
    }

    public void setHelper(ApiHelper helper) {
        ((PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("fragment")).setHelper(helper);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

        private TextView textView;
        private ApiHelper helper;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            rootView.findViewById(R.id.button_1).setOnClickListener(this);
            rootView.findViewById(R.id.button_2).setOnClickListener(this);
            textView = (TextView) rootView.findViewById(R.id.text);
            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            helper = new ApiHelper();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_1:
                    textView.setText("automated testing is great");
                    break;
                case R.id.button_2:
                    new FetchJSONTask().execute();
                    break;
            }
        }

        private class FetchJSONTask extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... params) {
                return helper.getJsonFromServer();
            }

            @Override
            protected void onPostExecute(String string) {
                textView.setText(string);
            }
        }

        public void setHelper(ApiHelper helper) {
            this.helper = helper;
        }
    }
}
