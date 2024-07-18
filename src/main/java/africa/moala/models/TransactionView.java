package africa.moala.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionView {

    
    private long id;
    
    private String transactionType;
    
    private double amount;
    
    private double amountPaid;
    
    private double amountReceived;
    
    private String transactionStatus;
    
    private String currencyMain;
    
    private String currencySecond;
    
    private String externalId;
    
    private String transactionLink;
    
    private String hash;
    
    private boolean sentInTheBlockchain;
    
    private int comfirmationNumber;

    private String createDateTime;

    private String updateDateTime;

    
    private String timeZone;
    
    private String idAppUserMain;
    
    private String usernameAppUserMain;
    
    private String idAppUserSecond;
    
    private String usernameAppUserSecond;
    
    private String paymentMethod;
    
    private String paymentMethodType;
    
    private String providerAPI;
    
    private String name;
    
    private boolean checkRefundManual;
    
    private boolean refundRequest;
    
    private String refundBy;


   private String serviceCode ;
    private String partnerId;

    private double commission;

    private double fees;

    String phoneNumber;



}
