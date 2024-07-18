# Moala SDK for JAVA

The Moala SDK for JAVA enables developers to easily integrate Moala API functionality into their JAVA applications. This SDK supports several operations such as balance checks, transactions checks, KYC checks, cashout, and cash-in.

## Prerequisites

- JAVA 8 or higher
- MAVEN to manage dependencies

## Installation

You can install the SDK via MAVEN. Add the SDK to your project using the following command:



## Configuration
To use the SDK, you need an API key and a secret key supplied by Moala. Here's how to configure and initialize the SDK:

```
import africa.moala;

String urlApi = "https://api.moala.africa";
String appKey = "cfa3a138-47c3-4f86-9fda";
String secretKey = "cfa3a138-47c3-4f86-9fda-d4dds3434";

MoalaClient moalaClient = new MoalaClient(appKey, secretKey, urlApi);
```

# Use

## Checking the balance
```
double res = moalaClient.getBalance();
System.out.println(res);
```

## Transaction verification
```
TransactionView transactionView = moalaClient.checkTransaction("finit_1");
        
System.out.println(transactionView);
        

```

## KYC verification
```
KycView kycView = moalaClient.getKycNumber("675598817","PAIEMENTMARCHAND_MTN_CM");
System.out.println(kycView);
```

## Make a cashin
```

TransactionView transactionView = moalaClient.cashin("675598817","CASHIN_MTN_CM",100, "finit_2");
 
System.out.println(transactionView);
  
```

## Make a cashout
```
TransactionView transactionView = moalaClient.cashout("675598817","PAIEMENTMARCHAND_MTN_CM",100, "finit_1");
 
System.out.println(transactionView);
```

# Error handling
Each method can raise an exception in the event of a query error. Errors are returned as an array containing the error message.

# Support
If you have any questions or problems, please open an issue in our [GitHub repository](https://github.com/bitkap/java-moala/issues).

# Contribution
Contributions to this project are welcome. You can contribute by improving the code, documentation or reporting bugs.
