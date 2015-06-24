package com.chetbox.fruit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class FruitListActivity extends AppCompatActivity {

    private static final String TAG = FruitListActivity.class.getSimpleName();
    private static final String FRUIT_URL = "http://private-e889-fruity.apiary-mock.com/list";
    private static final Type FRUIT_LIST_TYPE = new TypeToken<ArrayList<Fruit>>() {}.getType();

    private OkHttpClient mHttpClient = new OkHttpClient();
    private Gson mGson = new Gson();
    private ListView mFruitList;

    private class ShowFruitTask extends AsyncTask<Void, Void, List<Fruit>> {

        @Override
        protected List<Fruit> doInBackground(Void... params) {
            Request getReq = new Request.Builder()
                    .url(FRUIT_URL)
                    .build();
            try {
                Response resp = mHttpClient.newCall(getReq).execute();
                if (resp.isSuccessful()) {
                    return mGson.fromJson(resp.body().charStream(), FRUIT_LIST_TYPE);
                } else {
                    throw new RuntimeException("HTTP error: " + resp.code());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(List<Fruit> items) {
            mFruitList.setAdapter(new FruitListAdapter(FruitListActivity.this, items));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        mFruitList = (ListView) findViewById(android.R.id.list);

        new ShowFruitTask().execute();
    }
}
