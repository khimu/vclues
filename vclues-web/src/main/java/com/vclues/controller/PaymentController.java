package com.vclues.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.authorize.ResponseField;
import net.authorize.sim.Fingerprint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vclues.core.entity.Balance;
import com.vclues.core.entity.Payment;
import com.vclues.core.entity.User;
import com.vclues.core.service.IBalanceService;
import com.vclues.core.service.IPaymentService;

@Controller
@RequestMapping("/payment/")
public class PaymentController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	private String apiLoginId = "59Crh2Ar95b";

	private String transactionKey = "247QeK33QjdqZt3b";
	
	private String relayResponseUrl = "/payment/processPayment.htm";

	private String receiptPageUrl = "/order_receipt.jsp";
	
	@Autowired
	private IBalanceService balanceService;
	
	@Autowired
	private IPaymentService paymentService;

	@GetMapping(value="form")
	public String showFormRequest(Model model) {	
    	logger.info("Show all games");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
		Date today = new Date();
		
		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
 
	   	cal = Calendar.getInstance();
	   	cal.add(Calendar.MONTH, -1);
	   			   
	   	Balance balance = balanceService.findBalanceByUserId(user.getId());
	   
	   	model.addAttribute("balance", balance.getAmount());
	    model.addAttribute("apiLoginId", apiLoginId);
	    model.addAttribute("transactionKey", transactionKey);
	    model.addAttribute("relayResponseUrl", relayResponseUrl);

		return "payment/addFundsForm";
	}
		
	@PostMapping(value="submitAmount/{amount}")
	public String submitAmount(@PathVariable("amount") String amount, Model model) {
    	logger.info("Show all games");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}		
		
		/*
		 * track this in a table before submitting form to authorize net
		 */
	    Fingerprint fingerprint = Fingerprint.createFingerprint(apiLoginId, transactionKey,
	        1234567890,  // random sequence used for creating the finger print
	        amount);		
	    
	    model.addAttribute("fingerprint", fingerprint);
	    
		return "payment/fingerprint";
	}
	
	/*
	 * This call has to be refactored into its own web app and put on a server with restricted access.  Maybe the internal server where only
	 * specific ip's has access and authorize.net is one of the allowed ips.
	 */
	@PostMapping(value="processPayment")
	public String processShowFormRequest(HttpServletRequest request, Model model) {
		
    	logger.info("Show all games");
		User user = getLoggedInUser();
		if(user == null) {
			logger.info("Not able to retrieve user in session");
			return "redirect:/login";
		}
		
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
	  
		model.addAttribute("email", email);
		model.addAttribute("transactionId", transactionId);
		model.addAttribute("success", success);
		model.addAttribute("amount", amount);
		model.addAttribute("invoice", invoiceNumber);
		model.addAttribute("reason", reason);

	    if(email == null || StringUtils.isEmpty(StringUtils.trimWhitespace(email))) {
			receiptUrlBuffer.append("?");
			receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
			receiptUrlBuffer.append("Email is blank or null");
			
			if(timeInMillisecond != null && StringUtils.isEmpty(StringUtils.trimWhitespace(timeInMillisecond)) == false){
				long time =Long.parseLong(timeInMillisecond);
				logger.error("Email is blank or null for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
			}else{
				logger.error("Email is blank or null for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
			}
	    }
	    
	    
		Date createdOn = null;
		if(timeInMillisecond != null && StringUtils.isEmpty(StringUtils.trimWhitespace(timeInMillisecond)) == false) {
			long time =Long.parseLong(timeInMillisecond);
			createdOn = new Date(time);
		}

		try {
			Long userId = null;
			Balance balance = balanceService.findBalanceByUserId(user.getId());

			if(user == null){
				receiptUrlBuffer.append("?");
				receiptUrlBuffer.append(ResponseField.RESPONSE_REASON_TEXT.getFieldName()).append("=");
				receiptUrlBuffer.append("Account not found in portal");

				if(timeInMillisecond != null && StringUtils.isEmpty(StringUtils.trimWhitespace(timeInMillisecond)) == false) {
					long time =Long.parseLong(timeInMillisecond);
					logger.error("Account not found in portal " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
				}else{
					logger.error("Account not found in portal " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
				}
			}else{
				userId = user.getId();
			}

			Payment payment = new Payment(userId, transactionId, Double.parseDouble(amount), reason, success, invoiceNumber);
			paymentService.save(payment);
			
			balance.setAmount(balance.getAmount() + Double.parseDouble(amount));
			balanceService.save(balance);
			
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

			if(timeInMillisecond != null && StringUtils.isEmpty(StringUtils.trimWhitespace(timeInMillisecond)) == false) {
				long time =Long.parseLong(timeInMillisecond);
				//logger.error("Unable to record transaction for " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at " + format.format(new Date(time)));
			}else{
				//logger.error("Unable to record transaction for " + email + " for transaction " + result.getResponseMap().get(ResponseField.TRANSACTION_ID.getFieldName()) + " and amount " + result.getResponseMap().get(ResponseField.AMOUNT.getFieldName()) + " at ");
			}
		}

	    model.addAttribute("receiptUrlBuffer", receiptUrlBuffer.toString());
		return "payment/paymentProcessed";
	}	
	
}
