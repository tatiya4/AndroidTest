package com.codetest.demotest.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.codetest.demotest.Model.Animechan;
import com.codetest.demotest.R;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class TestDataAdapter extends RecyclerView.Adapter<TestDataAdapter.ViewHolder> {

    private ArrayList<Animechan> animechans;
    public Context mContext;
    View view;

    public TestDataAdapter(Context context, ArrayList<Animechan> category) {
        animechans = category;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType)
    {
        ViewHolder holder = null;
        try {


            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_data_view, parent, false);

            holder = new ViewHolder(view);

            final ViewHolder finalHolder = holder;

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("HardwareIds")
                @Override
                public void onClick(View v)
                {

                    try
                    {







                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            });



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        assert holder != null;
        return holder;
    }
    @SuppressLint({"HardwareIds", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)    {

        try
        {
            final Animechan animechan = animechans.get(position);

            String Anime = animechan.getAnime();
            String Character = animechan.getCharacter();
            String Quote = animechan.getQuote();


            holder.anime.setText(Anime);
            holder.character.setText(Character);
            holder.quote.setText(Quote);





        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public int getItemCount() {
        return animechans.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder    {

        TextView anime;
        TextView character;
        TextView quote;

        public ViewHolder(View itemView)
        {
            super(itemView);
            anime = itemView.findViewById(R.id.anime_name);
            character = itemView.findViewById(R.id.character_name);
            quote= itemView.findViewById(R.id.quote_name);


        }


    }


}
