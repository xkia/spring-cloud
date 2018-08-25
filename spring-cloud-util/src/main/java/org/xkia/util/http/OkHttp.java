package org.xkia.util.http;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author xkia
 */
@Slf4j
public class OkHttp {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");
  public static final MediaType MEDIA_TYPE_MARKDOWN
      = MediaType.parse("text/x-markdown; charset=utf-8");

  OkHttpClient client = new OkHttpClient();

  /**
   * http get
   */
  public String get(String url) {
    log.info("request get url : {}", url);
    Request request = new Request.Builder()
        .url(url)
        .build();

    try {
      Response response = client.newCall(request).execute();
      log.info("response get body : {}", response);
      return response.body().string();
    } catch (IOException e) {
      log.error("okHttp get is error", e);
    }
    return null;


  }


  /**
   * http post
   */
  public String post(String url, String param, MediaType mediaType) {
    log.info("request post : url {}, param : {}, mediaType : {}", url, param, mediaType);
    RequestBody body = RequestBody.create(mediaType, param);
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try {
      Response response = client.newCall(request).execute();
      log.info("response post body : {}", response);
      return response.body().string();
    } catch (IOException e) {
      log.error("okHttp post is error", e);
    }
    return null;
  }

}
