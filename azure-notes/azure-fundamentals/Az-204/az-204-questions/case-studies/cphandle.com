1.You need to retrieve the database connection string.
Azure database connection string retrieve REST API vault.azure.net/secrets/
1. cpandlkeyvault 2. Secret name: PostgreSQLConn 3. Querystring

2. You need to correct the corporate website error.
   Which four actions should you recommend be performed in sequence
   Step 1: Generate a certificate -
   Step 2: Upload the certificate to Azure Key Vault
   Scenario: All SSL certificates and credentials must be stored in Azure Key Vault.
   Step 3: Import the certificate to Azure App Service
   Step 4: Update line SCO5 of Security.cs to include error handling and then redeploy the code

3. You need to configure API Management for authentication
Policy -- validate jwt
Policy section outbound

4.You need to authenticate the user to the corporate website as indicated by the architectural
  diagram.
  Which two values should you use? Each correct answer presents part of the solution.
  NOTE: Each correct selection is worth one point.
  • A. ID token signature
  • B. ID token claims
  • C. HTTP response code
  • D. Azure AD endpoint URI
  • E. Azure AD tenant ID
  Answer: AD

5.You need to configure Azure Service Bus to Event Grid integration
tier - premium
rbac role - Contributor

6.You need to investigate the Azure Function app error message in the development
  environment.
  What should you do?
  • A. Connect Live Metrics Stream from Application Insights to the Azure Function app and filter the
  metrics.
  • B. Create a new Azure Log Analytics workspace and instrument the Azure Function app with
  Application Insights.
  • C. Update the Azure Function app with extension methods from Microsoft.Extensions.Logging to
  log events by using the log instance.
  • D. Add a new diagnostic setting to the Azure Function app to send logs to Log Analytics.

  ans is A


 7.You need to configure security and compliance for the corporate website files.
 Rbac
 azure storage account

 8.You need to correct the RequestUserApproval Function app error.
   What should you do?
   • A. Update line RA13 to use the async keyword and return an HttpRequest object value.
   • B. Configure the Function app to use an App Service hosting plan. Enable the Always On setting
   of the hosting plan.
   • C. Update the function to be stateful by using Durable Functions to process the request payload. ans
   • D. Update the functionTimeout property of the host.json project file to 15 minut

 9.You need to ensure that all messages from Azure Event Grid are processed.
   What should you use?
   • A. Azure Event Grid topic
   • B. Azure Service Bus topic
   • C. Azure Service Bus queue ans
   • D. Azure Storage queue
   • E. Azure Logic App custom connector