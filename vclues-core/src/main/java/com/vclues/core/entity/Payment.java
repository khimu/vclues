package com.vclues.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "transaction_id", unique = true)
	private String transactionId;
	
	@Column(name = "amount", columnDefinition = "DECIMAL(50,20)")
	private Double amount;
	
	@Column(name = "transaction_message")
	private String transactionMessage;
	private Integer success;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	public Payment(){}
	
	public Payment(Long userId, String transactionId, Double amount, String transactionMessage, Integer success, String invoiceNumber){		
		this.userId = userId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionMessage = transactionMessage;
		this.success = success;
		this.invoiceNumber = invoiceNumber;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionMessage() {
		return transactionMessage;
	}

	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	@Column(name = "user_id")
	public Long getUsersId() {
		return userId;
	}

	public void setUsersId(Long usersId) {
		this.userId = usersId;
	}

	@Column(name = "success")
	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
