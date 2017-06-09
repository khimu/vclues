package com.vclues.core.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
@Document(collection = "disputes")
public class Dispute implements Serializable {

	@Id
	private String id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Indexed(unique = false)
	@Field(value = "account_id")
	private String accountId;

	/*
	 * Combine account id, name and contact to make unique key
	 */
	@Indexed(unique = false)
	@Field(value = "descriptor_key")
	private String descriptorKey;

	/*
	 * Partner ID
	 */
	@Indexed(unique = false)
	@Field(value = "business_key")
	private String businessKey;

	@Field(value = "dispute_field")
	private List<DataField> disputeFields;

	@Indexed(unique = true)
	@Field(value = "cases_id")
	private String caseId;

	@Field(value = "dispute_amount")
	private String disputeAmount;

	@Field(value = "transaction_amount")
	private String transactionAmount;

	@Field(value = "refund_amount")
	private String refundAmount;

	@Indexed(unique = false)
	@Field(value = "arn")
	private String arn;

	@Indexed(unique = false)
	@Field(value = "status")
	private String status;

	@Field(value = "active")
	private Boolean active;

	private Date updated;

	private Date created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDescriptorKey() {
		return descriptorKey;
	}

	public void setDescriptorKey(String descriptorKey) {
		this.descriptorKey = descriptorKey;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getDisputeAmount() {
		return disputeAmount;
	}

	public void setDisputeAmount(String disputeAmount) {
		this.disputeAmount = disputeAmount;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getArn() {
		return arn;
	}

	public void setArn(String arn) {
		this.arn = arn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<DataField> getDisputeFields() {
		if (disputeFields == null) {
			disputeFields = new ArrayList<DataField>();
		}
		return disputeFields;
	}

	public void setDisputeFields(List<DataField> disputeFields) {
		this.disputeFields = disputeFields;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Dispute dispute = (Dispute) o;

		if (!caseId.equals(dispute.getCaseId()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		if(caseId == null) {
			return super.hashCode();
		}
		return caseId.hashCode();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
