package com.codetest.demotest.Activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.codetest.demotest.Adapters.TestDataAdapter;
import com.codetest.demotest.Model.Animechan;
import com.codetest.demotest.Network.Api;
import com.codetest.demotest.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{

    Context context;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerView = (RecyclerView)findViewById(R.id.recyclerViewForData);

            GetDetails();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void GetDetails()
    {
        try {

            if(isOnline())
            {
                OkHttpClient.Builder client1 = new OkHttpClient.Builder();
                client1.connectTimeout(5, TimeUnit.MINUTES);
                client1.readTimeout(5, TimeUnit.MINUTES);
                client1.build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL_NEW)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client1.build())
                        .build();

                Api api = retrofit.create(Api.class);

                Call<String> call = api.DataAPI();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response)
                    {

                        if(response.isSuccessful())
                        {
                            String response_Data = response.body();
                            assert response_Data != null;
                            DisplayData(response_Data);
                        }
                        else
                        {
                            ResponseBody responseBody = response.errorBody();
                            try {
                                assert responseBody != null;
                                String res = responseBody.string();
                                JSONObject jsonObject_res = new JSONObject(res);
                                JSONObject jsonObjecterror = jsonObject_res.getJSONObject("Error");
                                String msg = jsonObjecterror.optString("Message");
                                if(!msg.isEmpty())
                                {
                                       DisplayMessage(msg);
                                }
                                else
                                {
                                     DisplayMessage("Error In Service");
                                }


                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }


                        }


                    }

                    @Override
                    public void onFailure(@NotNull Call<String> call,@NotNull Throwable t) {
                         DisplayMessage("Error From Server...");


                    }
                });

            }
            else {
                DisplayMessage("Please connect internet first");
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void DisplayData(String res)
    {
        try {

            if(res!=null && !res.isEmpty())
            {
                JSONObject jsonObject = new JSONObject(res);

                String anime=jsonObject.optString("anime");
                String character=jsonObject.optString("character");
                String quote=jsonObject.optString("quote");

                ArrayList<Animechan> animechans = new ArrayList<>();
                animechans.add(new Animechan(anime, character, quote));

                TestDataAdapter testDataAdapter = new TestDataAdapter(context, animechans);
                recyclerView.setAdapter(testDataAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            }



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isOnline()
    {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    public void DisplayMessage(String msg)
    {
        try
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder
                    .setTitle("Message")
                    .setMessage(msg)
                    .setCancelable(false)
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            dialog.dismiss();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}