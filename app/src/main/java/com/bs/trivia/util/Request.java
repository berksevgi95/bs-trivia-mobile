package com.bs.trivia.util;

import okhttp3.OkHttpClient;

public class Request {

    public interface AsyncResponse {
        void run(String body);
    }

    public static class Response {
        String body;
        public Response(String body) {
            this.body = body;
        }
        public void then(AsyncResponse asyncResponse) {
            asyncResponse.run(body);
        }
    }

    public static Response get(String url) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(url);
        okhttp3.Request request = builder.build();

        try {
            okhttp3.Response response = client.newCall(request).execute();
            return new Response(response.body().string());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
