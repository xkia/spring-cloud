package org.xkia.util.http;


import java.util.Arrays;
import java.util.List;

public class OkHttpTest {

  @org.junit.Test
  public void get() {
    OkHttp okHttp = new OkHttp();

    String str = "1b2f16ab-f47c-494a-9b7c-71b32708c482, 1d00d2e6-653d-462f-8e7b-97314d7da577, 2376779e-eda7-43d9-aa66-2248447b2309, 2425a2eb-d2da-4699-852a-d049845f696d, 2c743bbe-d844-493a-a1cd-758568906fd5, 3956ede4-7cf3-464f-a2e6-965bfb84586b, 3ce8cf58-e020-487c-b54c-a8d89f238c8f, 4b2c9fbe-6bd3-4b0a-a0d0-ee44ae51fce0, 568fcb0c-1924-424e-8892-c6b460a49d20, 578a3909-671a-439a-8970-df13b7bd367d, 6cf910d2-47a5-45d2-b258-a81c21e09763, 78b314db-1074-4d66-a4ad-9d1657c0337f, 7ed82ab7-8b70-4e1b-afa8-6324d8aec5bc, 8392f64a-ef3a-4542-820c-e6f9c7ac5a77, 88064f3e-1014-442d-be98-2889de353618, 8db8fb0e-972c-4732-8962-877725f18c2f, 95be75e7-1ae5-40f9-8dea-49405e4efa67, 9ded398b-1bf5-4be2-be13-d06adbb90c1d, b0cad26c-0c41-4072-8416-eed6f5a0a29d, b800404a-9d96-4517-8867-f8914e2fa20c, bb1ececa-91c3-4696-b0c2-c2ef3d3caa40, c5802efe-d306-4a1e-a0fc-c5033b12d7cb, cf116702-9360-41b2-b959-742e59505b56, eb8c5513-9e9b-483f-9951-fb663941fa79, f0d14cd3-6522-443e-a5d5-574e85d0bdf4, f52ebabb-f655-4d12-88e1-a95a871e7aa6";
    String str1 = "OP370168910915190724&orderKey=1b2f16ab-f47c-494a-9b7c-71b32708c482, OP754169090919185010&orderKey=1d00d2e6-653d-462f-8e7b-97314d7da577, OP908244510911225130&orderKey=2376779e-eda7-43d9-aa66-2248447b2309, OP923973510910122303&orderKey=2425a2eb-d2da-4699-852a-d049845f696d, OP740665560924171304&orderKey=2c743bbe-d844-493a-a1cd-758568906fd5, OP178096370908153227&orderKey=3956ede4-7cf3-464f-a2e6-965bfb84586b, OP154655000904120744&orderKey=3ce8cf58-e020-487c-b54c-a8d89f238c8f, OP991958520904115010&orderKey=4b2c9fbe-6bd3-4b0a-a0d0-ee44ae51fce0, OP959129920916181223&orderKey=568fcb0c-1924-424e-8892-c6b460a49d20, OP159046090904182015&orderKey=578a3909-671a-439a-8970-df13b7bd367d, OP326355660903184000&orderKey=6cf910d2-47a5-45d2-b258-a81c21e09763, OP969410800919120410&orderKey=78b314db-1074-4d66-a4ad-9d1657c0337f, OP285858180927125711&orderKey=7ed82ab7-8b70-4e1b-afa8-6324d8aec5bc, OP463955550904152447&orderKey=8392f64a-ef3a-4542-820c-e6f9c7ac5a77, OP204231500923160501&orderKey=88064f3e-1014-442d-be98-2889de353618, OP451021000908143328&orderKey=8db8fb0e-972c-4732-8962-877725f18c2f, OP936909580913133155&orderKey=95be75e7-1ae5-40f9-8dea-49405e4efa67, OP253660150910115642&orderKey=9ded398b-1bf5-4be2-be13-d06adbb90c1d, OP264979650922153306&orderKey=b0cad26c-0c41-4072-8416-eed6f5a0a29d, OP458989430908171129&orderKey=b800404a-9d96-4517-8867-f8914e2fa20c, OP887267200922190134&orderKey=bb1ececa-91c3-4696-b0c2-c2ef3d3caa40, OP646389890910122707&orderKey=c5802efe-d306-4a1e-a0fc-c5033b12d7cb, OP837236270910123125&orderKey=cf116702-9360-41b2-b959-742e59505b56, OP102462180905122111&orderKey=eb8c5513-9e9b-483f-9951-fb663941fa79, OP436353600907131043&orderKey=f0d14cd3-6522-443e-a5d5-574e85d0bdf4, OP194289550911100246&orderKey=f52ebabb-f655-4d12-88e1-a95a871e7aa6";

    List<String> list = Arrays.asList(str.split(", "));
    List<String> list2 = Arrays.asList(str1.split(", "));

    String url = "http://pay.correct.hualala.com/order/restoreOrder?from=del&orderKey=";
    String url2 = "http://pay.correct.hualala.com/pay/notifyOldOrder?myOrderNo=";
    for(String s : list2){
      String result = okHttp.get( url2 + s );
      System.out.println(result);
    }
  }

  @org.junit.Test
  public void post() {
  }
}