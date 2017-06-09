package com.vclues.core.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.authorize.ResponseField;
import net.authorize.sim.Fingerprint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/payment/")
public class AuthorizeDotNetPaymentHandler {
	
	private final Logger logger = LoggerFactory.getLogger(AuthorizeDotNetPaymentHandler.class);

	private String apiLoginId = "59Crh2Ar95b";

	private String transactionKey = "247QeK33QjdqZt3b";
	
	private String relayResponseUrl = "/payment/processPayment.htm";

	private String receiptPageUrl = "/order_receipt.jsp";
	
	@RequestMapping(value="form.htm", method = RequestMethod.GET)
	public ModelAndView showFormRequest(Map<String, Object> model, HttpServletRequest request) {	
		
		Date today = new Date();
		
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
 
	   cal = Calendar.getInstance();
	   cal.add(Calendar.MONTH, -1);

	   	//model.put("balance", balance.getAmount());
	    model.put("apiLoginId", apiLoginId);
	    model.put("transactionKey", transactionKey);
	    model.put("relayResponseUrl", relayResponseUrl);

		return new ModelAndView("payment/addFundsForm", model);
	}
		
	@RequestMapping(value="submitAmount.htm", method = RequestMethod.POST)
	public ModelAndView submitAmount(@RequestParam("amount") String amount, Map<String, Object> model, HttpServletRequest request) {
		
		/*
		 * track this in a table before submitting form to authorize net
		 */
	    Fingerprint fingerprint = Fingerprint.createFingerprint(apiLoginId, transactionKey,
	        1234567890,  // random sequence used for creating the finger print
	        amount);		
	    
	    model.put("fingerprint", fingerprint);
	    
		return new ModelAndView("payment/fingerprint", model);
	}
	
	/*
	 * This call has to be refactored into its own web app and put on a server with restricted access.  Maybe the internal server where only
	 * specific ip's has access and authorize.net is one of the allowed ips.
	 */
	@RequestMapping(value="processPayment.htm", method = RequestMethod.POST)
	public ModelAndView processShowFormRequest(HttpServletRequest request, Map<String, Object> model) {
	    /*
	     * Leave the MD5HashKey as is - unless you have explicitly set it in the
	     * merchant interface: Account > Settings > Security Settings > MD5-Hash
	     */ 
	    String MD5HashKey = "8Nbj5A3M";

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	    StringBuffer receiptUrlBuffer = new StringBuffer(receiptPageUrl);
	    net.authorize.sim.Result result = net.authorize.sim.Result.createResult(apiLoginId, MD5HashKey, request.getParameterMap());

	    String email = result.getResponseMap().get(ResponseField.EMAIL_ADDRESS.getFieldName());
		String timeInMillisecond = result.getResponseMap().get(ResponseField.INVOICE_NUMBER.getFieldName());

		// Show the confirmation data
		Map<String, String[]> requestParameterMap = request.getParameterMap();

		String transactionId = "0";
		String amount = requestParameterMap.get(ResponseField.AMOUNT.getFieldName())[0];
		String invoiceNumber = requestParameterMap.get(ResponseField.INVOICE_NUMBER.getFieldName())[0];
		String reason = net.authorize.util.StringUtils.sanitizeString(requestParameterMap.get(ResponseField.RESPONSE_REASON_TEXT.getFieldName())[0]);
		Integer success = Integer.parseInt(requestParameterMap.get(ResponseField.RESPONSE_CODE.getFieldName())[0]);

	  if(requestParameterMap != null && requestParameterMap.containsKey(ResponseField.RESPONSE_CODE.getFieldName())) {
	    if(requestParameterMap.containsKey(ResponseField.TRANSACTION_ID.getFieldName())) {
	    	transactionId = net.authorize.util.StringUtils.sanitizeString(requestParameterMap.get(ResponseField.TRANSACTION_ID.getFieldName())[0]);
	    }
	  }
	  
		model.put("email", email);
		model.put("transactionId", transactionId);
		model.put("success", success);
		model.put("amount", amount);
		model.put("invoice", invoiceNumber);
		model.put("reason", reason);

	    if(StringUtils.isEmptyOrWhitespaceOnly(email)){
			receiptUrlBuffer.append("?");
			receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
			receiptUrlBuffer.append("Email is blank or null");
			
			if(StringUtils.isEmptyOrWhitespaceOnly(timeInMillisecond) == false){
				long time =Long.parseLong(timeInMillisecond);
				logger.error("Email is blank or null for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
			}else{
				logger.error("Email is blank or null for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
			}
	    }
	    
		Date createdOn = null;
		if(StringUtils.isEmptyOrWhitespaceOnly(timeInMillisecond) == false){
			long time =Long.parseLong(timeInMillisecond);
			createdOn = new Date(time);
		}

		try {
			Long userId = null;

			
				receiptUrlBuffer.append("?");
				receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
				receiptUrlBuffer.append("Account not found in portal");

				if(StringUtils.isEmptyOrWhitespaceOnly(timeInMillisecond) == false){
					long time =Long.parseLong(timeInMillisecond);
					logger.error("Account not found in portal " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
				}else{
					logger.error("Account not found in portal " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
				}
			

			
			
		    // perform Java server side processing...
		    // ...
		    // build receipt url buffer

		    if(result != null) {
		      receiptUrlBuffer.append("?");
		      receiptUrlBuffer.append(ResponseField.RESPONSE_CODE.getFieldName()).append("=");
		      receiptUrlBuffer.append(result.getResponseCode().getCode());
		      receiptUrlBuffer.append("&");
		      receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_CODE.getFieldName()).append("=");
		      receiptUrlBuffer.append(result.getReasonResponseCode().getResponseReasonCode());
		      receiptUrlBuffer.append("&");
		      receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
		      receiptUrlBuffer.append(result.getResponseMap().get(ResponseField.RESPONSE_REASON_TEXT.getFieldName()));

		      if(result.isApproved()) {
		        receiptUrlBuffer.append("&").append(ResponseField.TRANSACTION_ID.getFieldName()).append("=").append(transactionId);
		        receiptUrlBuffer.append("&").append(ResponseField.AMOUNT.getFieldName()).append("=").append(amount);
				receiptUrlBuffer.append("&").append(ResponseField.EMAIL_ADDRESS.getFieldName()).append("=").append(email);
		        //receiptUrlBuffer.append("&").append(ResponseField.INVOICE_NUMBER.getFieldName()).append("=").append(payment.getCreatedOn().toString());
		      }
		    }
		}catch(Exception e){
			receiptUrlBuffer.append("?");
			receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
			receiptUrlBuffer.append("Unable to record transaction");

			if(StringUtils.isEmptyOrWhitespaceOnly(timeInMillisecond) == false){
				long time =Long.parseLong(timeInMillisecond);
				//logger.error("Unable to record transaction for " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
			}else{
				//logger.error("Unable to record transaction for " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
			}
		}

	    model.put("receiptUrlBuffer", receiptUrlBuffer.toString());
		return new ModelAndView("payment/paymentProcessed", model);
	}	
	
}
