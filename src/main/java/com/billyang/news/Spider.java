package com.billyang.news;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.TimerTask;

public class Spider extends TimerTask {

    String path;

    public Spider(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY), year = c.get(Calendar.YEAR), month = c.get(Calendar.MONTH) + 1, day = c.get(Calendar.DAY_OF_MONTH);

        if(hour > 1) {
            URL url = null;
            try {
                url = new URL("https://api.iyk0.com/60s/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();

                if(code == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while((line = reader.readLine()) != null) builder.append(line).append("\n");
                    String content = builder.toString();

                    JSONObject jsonObject = JSONObject.parseObject(content);
                    String imgURL = jsonObject.getString("imageUrl");

                    url = new URL(imgURL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inStream = urlConnection.getInputStream();
                    FileOutputStream fs = new FileOutputStream(new File(path, year + "-" + month + "-" + day + ".png"));

                    byte[] buffer = new byte[1204];
                    int byteread;

                    while ((byteread = inStream.read(buffer)) != -1) fs.write(buffer, 0, byteread);

                    inStream.close();
                    fs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
