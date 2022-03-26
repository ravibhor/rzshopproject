package com.rz.core.shop.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotEmpty;

@Embeddable
public class Billing {

	//@NotEmpty
	@Column(name = "BILLING_LAST_NAME", length = 64)
	private String lastName;

	//@NotEmpty
	@Column(name = "BILLING_FIRST_NAME", length = 64)
	private String firstName;

	@Column(name = "BILLING_COMPANY", length = 100)
	private String company;

	@Column(name = "BILLING_STREET_ADDRESS", length = 256)
	private String address;

	@Column(name = "BILLING_CITY", length = 100)
	private String city;

	@Column(name = "BILLING_POSTCODE", length = 20)
	private String postalCode;

	@Column(name = "BILLING_TELEPHONE", length = 32)
	private String telephone;

	@Column(name = "BILLING_STATE", length = 100)
	private String state;

	@Column(name = "LONGITUDE", length = 100)
	private String longitude;

	@Column(name = "LATITUDE", length = 100)
	private String latitude;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	@JoinColumn(name = "BILLING_COUNTRY_ID", nullable = true)
	private Country country;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Zone.class)
	@JoinColumn(name = "BILLING_ZONE_ID", nullable = true)
	private Zone zone;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Billing() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Billing(@NotEmpty String lastName, @NotEmpty String firstName, String company, String address, String city,
			String postalCode, String telephone, String state, String longitude, String latitude, Country country,
			Zone zone) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.company = company;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.telephone = telephone;
		this.state = state;
		this.longitude = longitude;
		this.latitude = latitude;
		this.country = country;
		this.zone = zone;
	}

	@Override
	public String toString() {
		return "Billing [lastName=" + lastName + ", firstName=" + firstName + ", company=" + company + ", address="
				+ address + ", city=" + city + ", postalCode=" + postalCode + ", telephone=" + telephone + ", state="
				+ state + ", longitude=" + longitude + ", latitude=" + latitude + ", country=" + country + ", zone="
				+ zone + "]";
	}

}
