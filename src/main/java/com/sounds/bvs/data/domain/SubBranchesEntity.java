package com.sounds.bvs.data.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SUB_BRANCH_HDR_TB")
public class SubBranchesEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SYS_ID")
	private Long id;
	
	@Column(name="BRANCH_NAME", length=50)
	private String branchName;
	
	@Column(name="BRANCH_CODE", length=30)
	private String branchCode;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLINET_ID")
	private ClientsEntity clients;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BRNCH_ID")
	private BranchOfficeEntity branchOffice;
	
	@Embedded
	private AddressEmbeddable address;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public ClientsEntity getClients() {
		return clients;
	}

	public void setClients(ClientsEntity clients) {
		this.clients = clients;
	}

	public BranchOfficeEntity getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOfficeEntity branchOffice) {
		this.branchOffice = branchOffice;
	}

	public AddressEmbeddable getAddress() {
		return address;
	}

	public void setAddress(AddressEmbeddable address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubBranchesEntity other = (SubBranchesEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
