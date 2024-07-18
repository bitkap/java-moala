package africa.moala;

import africa.moala.models.KycView;
import africa.moala.models.TransactionView;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
public class MoalaClient {

  private String appKey;

  private String secretKey;

  private String urlApi;

  private RestTemplate restTemplate =  new RestTemplate();

  public MoalaClient(String appKey, String secretKey, String urlApi) {
    this.appKey = appKey;
    this.secretKey = secretKey;
    this.urlApi = urlApi;
  }

  public Double getBalance(){

    Double res;

    Long epoch = Instant.now().getEpochSecond();
    String message = epoch+"GET"+"/v1/api/balance";

    String signatureSyn = HmacUtils.hmacSha256Hex(secretKey, message);

    final HttpHeaders headers = new HttpHeaders();
    headers.set("LP-ACCESS-SIGN", signatureSyn);
    headers.set("Content-Type", "application/json");
    headers.set("LP-ACCESS-TIMESTAMP", ""+epoch);
    headers.set("LP-ACCESS-KEY", appKey);
    final HttpEntity<String> entity = new HttpEntity<String>(headers);

      res = restTemplate.exchange(urlApi + "/v1/api/balance", HttpMethod.GET, entity, Double.class).getBody();

    return res;
  }

  public TransactionView checkTransaction( String partnerId){

    Long epoch = Instant.now().getEpochSecond();
    String message = epoch+"GET"+"/v1/api/transaction/check/"+partnerId;

    String signatureSyn = HmacUtils.hmacSha256Hex(secretKey, message);

    final HttpHeaders headers = new HttpHeaders();
    headers.set("LP-ACCESS-SIGN", signatureSyn);
    headers.set("Content-Type", "application/json");
    headers.set("LP-ACCESS-TIMESTAMP", ""+epoch);
    headers.set("LP-ACCESS-KEY", appKey);
    final HttpEntity<String> entity = new HttpEntity<String>(headers);

    TransactionView res = restTemplate.exchange(urlApi +"/v1/api/transaction/check/"+partnerId, HttpMethod.GET, entity, TransactionView.class).getBody();

    return res;
  }

  public KycView getKycNumber ( String phoneNumber, String serviceCode){

    Long epoch = Instant.now().getEpochSecond();
    String message = epoch+"GET"+"/v1/api/kyc/"+serviceCode+"/"+phoneNumber;

    String signatureSyn = HmacUtils.hmacSha256Hex(secretKey, message);

    final HttpHeaders headers = new HttpHeaders();
    headers.set("LP-ACCESS-SIGN", signatureSyn);
    headers.set("Content-Type", "application/json");
    headers.set("LP-ACCESS-TIMESTAMP", ""+epoch);
    headers.set("LP-ACCESS-KEY", appKey);
    final HttpEntity<String> entity = new HttpEntity<String>(headers);

    KycView res = restTemplate.exchange(urlApi +"/v1/api/kyc/"+serviceCode+"/"+phoneNumber, HttpMethod.GET, entity, KycView.class).getBody();

    return res;
  }

  public TransactionView cashout ( String phoneNumber, String serviceCode, double amount, String partnerId){


    Map resq = new HashMap<>();

    resq.put("amount", amount);
    resq.put("transactionType", "deposit");
    resq.put("serviceCode", serviceCode);
    resq.put("phoneNumber", phoneNumber);
    resq.put("partnerId", partnerId);

    String bodyJson = new Gson().toJson(resq);

    Long epoch = Instant.now().getEpochSecond();
    String message = epoch+"POST"+"/v1/api/transaction/payment"+bodyJson ;


    String signatureSyn = HmacUtils.hmacSha256Hex(secretKey, message);

    final HttpHeaders headers = new HttpHeaders();
    headers.set("LP-ACCESS-SIGN", signatureSyn);
    headers.set("Content-Type", "application/json");
    headers.set("LP-ACCESS-TIMESTAMP", ""+epoch);
    headers.set("LP-ACCESS-KEY", appKey);
    final HttpEntity<String> entity = new HttpEntity<String>(bodyJson,headers);

    TransactionView res = restTemplate.exchange(urlApi+"/v1/api/transaction/payment", HttpMethod.POST, entity, TransactionView.class).getBody();

    return  res;
  }  public TransactionView cashin ( String phoneNumber, String serviceCode, double amount, String partnerId){


    Map resq = new HashMap<>();

    resq.put("amount", amount);
    resq.put("transactionType", "withdrawal");
    resq.put("serviceCode", serviceCode);
    resq.put("phoneNumber", phoneNumber);
    resq.put("partnerId", partnerId);

    String bodyJson = new Gson().toJson(resq);

    Long epoch = Instant.now().getEpochSecond();
    String message = epoch+"POST"+"/v1/api/transaction/withdrawal"+bodyJson ;


    String signatureSyn = HmacUtils.hmacSha256Hex(secretKey, message);

    final HttpHeaders headers = new HttpHeaders();
    headers.set("LP-ACCESS-SIGN", signatureSyn);
    headers.set("Content-Type", "application/json");
    headers.set("LP-ACCESS-TIMESTAMP", ""+epoch);
    headers.set("LP-ACCESS-KEY", appKey);
    final HttpEntity<String> entity = new HttpEntity<String>(bodyJson,headers);
    TransactionView res = restTemplate.exchange(urlApi+"/v1/api/transaction/withdrawal", HttpMethod.POST, entity, TransactionView.class).getBody();
    return  res;
  }

}
