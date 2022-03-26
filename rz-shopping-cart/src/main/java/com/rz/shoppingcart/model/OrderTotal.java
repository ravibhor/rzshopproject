package com.rz.shoppingcart.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;


/*@Entity
@Table (name="ORDER_TOTAL" )*/
public class OrderTotal extends AbstractEntity {
	private static final long serialVersionUID = -5885315557404081674L;
	
	@Id
	@TableGenerator(name = "ORDER__TOTAL_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", 
			pkColumnValue = "ORDER_TOTAL_SEQ_NEXT_VAL", initialValue = 701, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDER_TOTAL_GEN")
	private Long id;
	
	@Column (name ="CODE", nullable=false)
	private String orderTotalCode;//SHIPPING, TAX
	
	@Column (name ="TITLE", nullable=true)
	private String title;
	
	@Column (name ="TEXT", nullable=true)
	@Type(type = "org.hibernate.type.TextType")
	private String text;
	
	@Column (name ="VALUE", precision=15, scale=4, nullable=false )
	private BigDecimal value;
	
	@Column (name ="MODULE", length=60 , nullable=true )
	private String module;
	
	
	@Column (name ="SORT_ORDER", nullable=false)
	private int sortOrder;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "ORDER_ID", nullable=false)
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderTotalCode() {
		return orderTotalCode;
	}

	public void setOrderTotalCode(String orderTotalCode) {
		this.orderTotalCode = orderTotalCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderTotal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderTotal(Long id, String orderTotalCode, String title, String text, BigDecimal value, String module,
			int sortOrder, Order order) {
		super();
		this.id = id;
		this.orderTotalCode = orderTotalCode;
		this.title = title;
		this.text = text;
		this.value = value;
		this.module = module;
		this.sortOrder = sortOrder;
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderTotal [id=" + id + ", orderTotalCode=" + orderTotalCode + ", title=" + title + ", text=" + text
				+ ", value=" + value + ", module=" + module + ", sortOrder=" + sortOrder + ", order=" + order + "]";
	}
	


}