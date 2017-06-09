package com.vclues.core.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A message between 2 users
 * 
 * @author khimung
 *
 */
@XmlRootElement
@Document(collection = "descriptors")
public class Descriptor extends Base {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	/*
	 * Merchant ID
	 */
	@Indexed(unique=false)
	@Field(value="account_id")
	private String accountId;
	
	@Indexed(unique=false)
	@Field(value="parent_account_id")
	private String parentAccountId;
	
	/*
	 * Combine business key, name and contact to make unique key
	 * 
	 * cannot have duplicates within the same partner
	 */
	@Indexed(unique=true)
	@Field(value="descriptor_key")
	private String descriptorKey;
	
	/*
	 * Partner ID
	 */
	@Indexed(unique=false)
	@Field(value="business_key")
	private String businessKey;
	
	@Indexed(unique=false)
	@Field(value="name")
	private String name;
	
	@Indexed(unique=false)
	@Field(value="contact")
	private String contact;
	
	@Field(value="industry")
	private String industry;
	
	@Field(value="processor_gateway")
	private String processorGateway;
	
	@Field(value="start_date")
	private Date startDate;
	
	@Field(value="end_date")
	private Date endDate;
	
	@Field(value="transaction_count")
	private Integer transactionCount;
	
	/*
	 * Txn $ Volume
	 */
	@Field(value="gross_revenue")
	private Double grossRevenue;
	
	/*
	 * CB Count (pm)
	 */
	@Field(value="chargeback_total_count")
	private Integer chargebackTotalCount;
	
	/*
	 * CB $ Volume (pm)	
	 */
	@Field(value="chargeback_total_amount")
	private Double chargebackTotalAmount;
	
	@Field(value="average_chargeback_amount")
	private Double averageChargebackAmount;
	
	/*
	 * Avg Txn Value	
	 */
	@Field(value="average_transaction_amount")
	private Double averageTransactionAmount;
	
	/*
	 * CB Ratio
	 */
	@Field(value="chargeback_ratio")
	private Double chargebackRatio;
	
	/*
	 * Contract Rate
	 */
	@Field(value="contract_rage")
	private Double contractRate;


	@Field(value="descriptor_field")
	private List<DataField> descriptorFields;
	
	@Field(value="active")
	private Boolean active;
	
	private Date updated;

	private Date created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProcessorGateway() {
		return processorGateway;
	}

	public void setProcessorGateway(String processorGateway) {
		this.processorGateway = processorGateway;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(Integer transactionCount) {
		this.transactionCount = transactionCount;
	}

	public Double getGrossRevenue() {
		return grossRevenue;
	}

	public void setGrossRevenue(Double grossRevenue) {
		this.grossRevenue = grossRevenue;
	}

	public Integer getChargebackTotalCount() {
		return chargebackTotalCount;
	}

	public void setChargebackTotalCount(Integer chargebackTotalCount) {
		this.chargebackTotalCount = chargebackTotalCount;
	}

	public Double getChargebackTotalAmount() {
		return chargebackTotalAmount;
	}

	public void setChargebackTotalAmount(Double chargebackTotalAmount) {
		this.chargebackTotalAmount = chargebackTotalAmount;
	}

	public Double getAverageChargebackAmount() {
		return averageChargebackAmount;
	}

	public void setAverageChargebackAmount(Double averageChargebackAmount) {
		this.averageChargebackAmount = averageChargebackAmount;
	}

	public Double getAverageTransactionAmount() {
		return averageTransactionAmount;
	}

	public void setAverageTransactionAmount(Double averageTransactionAmount) {
		this.averageTransactionAmount = averageTransactionAmount;
	}

	public Double getChargebackRatio() {
		return chargebackRatio;
	}

	public void setChargebackRatio(Double chargebackRatio) {
		this.chargebackRatio = chargebackRatio;
	}

	public Double getContractRate() {
		return contractRate;
	}

	public void setContractRate(Double contractRate) {
		this.contractRate = contractRate;
	}

	public List<DataField> getDescriptorFields() {
		if(descriptorFields == null) {
			descriptorFields = new ArrayList<DataField>();
		}
		return descriptorFields;
	}

	public void setDescriptorFields(List<DataField> descriptorFields) {
		this.descriptorFields = descriptorFields;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Descriptor descriptor = (Descriptor) o;

		if (!descriptorKey.equals(descriptor.getDescriptorKey()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		if(descriptorKey == null) {
			return super.hashCode();
		}
		
		return descriptorKey.hashCode();
	}
	
	@Override
	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	 }
}
