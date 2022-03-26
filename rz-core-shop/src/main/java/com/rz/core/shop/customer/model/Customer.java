package com.rz.core.shop.customer.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.base.enums.CustomerGender;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.common.model.BaseEntity;
import com.rz.core.shop.user.model.Authority;
import com.rz.core.shop.user.model.Role;

@Entity
@Table(name = "CUSTOMER", uniqueConstraints = { @UniqueConstraint(columnNames = "CUSTOMER_EMAIL_ADDRESS") })
public class Customer extends BaseEntity implements Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	 * 
	 * @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName =
	 * "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue =
	 * "CUSTOMER_SEQ_NEXT_VAL", initialValue = 15, allocationSize = 1)
	 * 
	 * @GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOMER_GEN")
	 * private long id;
	 */
	@Column(name = "user_name")
	private String username;

	@Column(name = "CUSTOMER_GENDER", length = 1, nullable = true)
	@Enumerated(value = EnumType.STRING)
	private CustomerGender gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CUSTOMER_DOB")
	private Date dateOfBirth;

	@Column(name = "CUSTOMER_EMAIL_ADDRESS")
	private String email;

	@Column(name = "CUSTOMER_NICK", length = 96)
	private String nick;// unique username

	@Column(name = "CUSTOMER_FIRSTNAME", length = 96)
	private String firstName;

	@Column(name = "CUSTOMER_LASTNAME", length = 96)
	private String lastName;

	@Column(name = "CUSTOMER_MOBILE", length = 10)
	private String mobile;

	@Column(name = "CUSTOMER_COMPANY", length = 100)
	private String company;

	@Column(name = "CUSTOMER_PASSWORD", length = 60)
	private String password;

	@Column(name = "CUSTOMER_ANONYMOUS")
	private boolean anonymous;

	@Column(name = "REVIEW_COUNT")
	private Integer customerReviewCount;

	@Column(name = "PROVIDER")
	private String provider;

	// @Embedded
	private String deliveryAddress;

	// @Valid
	// @Embedded
	private String billingAddress;

	private boolean enabled = true;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CUSTOMER_ROLE", joinColumns = {
			@JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
	private Set<Role> customerRoles = new HashSet<>();

	@JsonIgnore
	@Transient
	private String showCustomerStateList;

	@JsonIgnore
	@Transient
	private String showBillingStateList;

	@JsonIgnore
	@Transient
	private String showDeliveryStateList;

	public CustomerGender getGender() {
		return gender;
	}

	public void setGender(CustomerGender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Integer getCustomerReviewCount() {
		return customerReviewCount;
	}

	public void setCustomerReviewCount(Integer customerReviewCount) {
		this.customerReviewCount = customerReviewCount;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Set<Role> getCustomerRoles() {
		return customerRoles;
	}

	public void setCustomerRoles(Set<Role> customerRoles) {
		this.customerRoles = customerRoles;
	}

	public String getShowCustomerStateList() {
		return showCustomerStateList;
	}

	public void setShowCustomerStateList(String showCustomerStateList) {
		this.showCustomerStateList = showCustomerStateList;
	}

	public String getShowBillingStateList() {
		return showBillingStateList;
	}

	public void setShowBillingStateList(String showBillingStateList) {
		this.showBillingStateList = showBillingStateList;
	}

	public String getShowDeliveryStateList() {
		return showDeliveryStateList;
	}

	public void setShowDeliveryStateList(String showDeliveryStateList) {
		this.showDeliveryStateList = showDeliveryStateList;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		this.customerRoles.forEach(customerRole -> {
			set.add(new Authority(customerRole.getRoleName()));
		});
		return set;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(String username, CustomerGender gender, Date dateOfBirth, String email, String nick,
			String firstName, String lastName, String mobile, String company, String password, boolean anonymous,
			Integer customerReviewCount, String provider, String deliveryAddress, String billingAddress,
			boolean enabled, Set<Role> customerRoles, String showCustomerStateList, String showBillingStateList,
			String showDeliveryStateList) {
		super();
		this.username = username;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.nick = nick;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.company = company;
		this.password = password;
		this.anonymous = anonymous;
		this.customerReviewCount = customerReviewCount;
		this.provider = provider;
		this.deliveryAddress = deliveryAddress;
		this.billingAddress = billingAddress;
		this.enabled = enabled;
		this.customerRoles = customerRoles;
		this.showCustomerStateList = showCustomerStateList;
		this.showBillingStateList = showBillingStateList;
		this.showDeliveryStateList = showDeliveryStateList;
	}

	public Customer() {
		super();
	}

	public Customer(long id, Date createdAt, String createdBy, Date updatedAt, String updatedBy, StatusEnum status) {
		super(id, createdAt, createdBy, updatedAt, updatedBy, status);
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", email="
				+ email + ", nick=" + nick + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile="
				+ mobile + ", company=" + company + ", password=" + password + ", anonymous=" + anonymous
				+ ", customerReviewCount=" + customerReviewCount + ", provider=" + provider + ", deliveryAddress="
				+ deliveryAddress + ", billingAddress=" + billingAddress + ", enabled=" + enabled + ", customerRoles="
				+ customerRoles + ", showCustomerStateList=" + showCustomerStateList + ", showBillingStateList="
				+ showBillingStateList + ", showDeliveryStateList=" + showDeliveryStateList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (anonymous ? 1231 : 1237);
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((customerReviewCount == null) ? 0 : customerReviewCount.hashCode());
		result = prime * result + ((customerRoles == null) ? 0 : customerRoles.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((showBillingStateList == null) ? 0 : showBillingStateList.hashCode());
		result = prime * result + ((showCustomerStateList == null) ? 0 : showCustomerStateList.hashCode());
		result = prime * result + ((showDeliveryStateList == null) ? 0 : showDeliveryStateList.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (anonymous != other.anonymous)
			return false;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (customerReviewCount == null) {
			if (other.customerReviewCount != null)
				return false;
		} else if (!customerReviewCount.equals(other.customerReviewCount))
			return false;
		if (customerRoles == null) {
			if (other.customerRoles != null)
				return false;
		} else if (!customerRoles.equals(other.customerRoles))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (showBillingStateList == null) {
			if (other.showBillingStateList != null)
				return false;
		} else if (!showBillingStateList.equals(other.showBillingStateList))
			return false;
		if (showCustomerStateList == null) {
			if (other.showCustomerStateList != null)
				return false;
		} else if (!showCustomerStateList.equals(other.showCustomerStateList))
			return false;
		if (showDeliveryStateList == null) {
			if (other.showDeliveryStateList != null)
				return false;
		} else if (!showDeliveryStateList.equals(other.showDeliveryStateList))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
