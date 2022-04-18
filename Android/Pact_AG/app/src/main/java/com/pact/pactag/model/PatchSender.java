package com.pact.pactag.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PatchSender {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private void patchRequest(String patchUrl, JSONObject postBody)  {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, postBody.toString());
        Request request = new Request.Builder().url(patchUrl).patch(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG",response.body().string());
            }
        });
    }

    /**
     *
     * @param url url pour atteindre la ressource dans la base de données:la pompe
     * @param boxAgIpAdress ip de la box (microcontroleur)
     * @param state         l'état de la pompo (on/of true/false)
     */
    public void setState(String url,String boxAgIpAdress,boolean state){
        JSONObject obj = new JSONObject();
        try {
            obj.put("state",state);
            obj.put("ipAdress",boxAgIpAdress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        patchRequest(url,obj);
    }

    /**
     *
     * @param url url pour atteindre la ressource dans la base de données:la lampe
     * @param boxAgIpAdress ip de la box (microcontroleur)
     * @param luxValue la valeur de l'intensité lumineuse
     */

    public void setLuxValue(String url,String boxAgIpAdress,int luxValue){
        JSONObject obj =  new JSONObject();
        try {
            obj.put("value",luxValue);
            obj.put("ipAdress",boxAgIpAdress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        patchRequest(url,obj);
    }
}


