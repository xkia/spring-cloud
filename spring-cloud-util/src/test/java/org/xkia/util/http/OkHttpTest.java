package org.xkia.util.http;


import java.util.Arrays;
import java.util.List;

public class OkHttpTest {

  @org.junit.Test
  public void get() {
    OkHttp okHttp = new OkHttp();

    String str = "018aa846-bd2f-4971-8b06-e59414148b0b, 1a87ee9a-ac6c-4445-8ce2-323c7cf3bb32, 1e295555-cfd8-40c0-9bfe-784de4e6fdfd, 3140c55c-5e08-4bff-978d-8a3a68cb999f, 40a62bc8-358f-4505-b054-c2d47bfa160f, 4f5aacd1-1189-442d-8e94-6d5f5967d407, 60d425b6-bcf7-41f5-a4f2-321a419111f6, 672a1d8d-5fc5-4b96-81b0-422789c05917, 6bae6050-0adc-4f57-b37f-8d09103e80f1, 78ff2ee0-2190-4e08-8c3e-0c75d9f099bb, aeb74c73-5fad-4489-8b68-caad7bc18b10, ff6af2a1-7422-4b71-80be-f81303c52bdd";


    String str1 = "7c5f27e4-64d7-43fd-87d8-3aacf3c7e65a&myOrderNo=OP498636911103201101, 87b6d21c-e04d-4b6d-b73f-baf08d52112d&myOrderNo=OP304798461102174243, c82b6798-1505-402b-94cc-afa69474cb1e&myOrderNo=OP639952211102185317, d2190f7f-85de-4d44-b985-2a9eb77f4282&myOrderNo=OP530695341103131603";

    List<String> list = Arrays.asList(str.split(", "));
    List<String> list2 = Arrays.asList(str1.split(", "));

    String url = "http://pay.correct.hualala.com/order/restoreOrder?from=del&orderKey=";
    String url2 = "http://pay.correct.hualala.com/pay/notifyOldOrder?orderKey=";
    for(String s : list2){
      String result = okHttp.get( url2 + s );
      System.out.println(result);
    }
  }

  @org.junit.Test
  public void post() {
  }
}