package com.rz.core.shop.common.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COUNTRY")
@Cacheable
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
	private Set<Zone> zones = new HashSet<Zone>();
	
	@Column(name = "COUNTRY_SUPPORTED")
	private boolean supported = true;
	
	@Column(name = "COUNTRY_ISOCODE", unique=true, nullable = false)
	private String isoCode;
	
	@Transient
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Zone> getZones() {
		return zones;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}

	public boolean isSupported() {
		return supported;
	}

	public void setSupported(boolean supported) {
		this.supported = supported;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Country(Integer id, Set<Zone> zones, boolean supported, String isoCode, String name) {
		super();
		this.id = id;
		this.zones = zones;
		this.supported = supported;
		this.isoCode = isoCode;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", zones=" + zones + ", supported=" + supported + ", isoCode=" + isoCode
				+ ", name=" + name + "]";
	}

}
