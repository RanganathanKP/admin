package com.spares.admin.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "spareorder")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer orderId;
	@Column(name="order_totalamount")
	private String ordertotaltmount;
    @Column(name="user_id")
	private String userid;

    @Column(name="order_creation_time")
    @UpdateTimestamp
	private LocalDateTime ordercreationtime;
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
	private List<OrderDetailEntity> orderDetailEntityList;

	public OrderEntity(Integer orderId, String ordertotaltmount, List<OrderDetailEntity> orderDetailEntityList, String userid, String productDealername, LocalDateTime ordercreationtime) {
		this.orderId = orderId;
		this.ordertotaltmount = ordertotaltmount;
		this.orderDetailEntityList = orderDetailEntityList;
		this.userid = userid;
		this.ordercreationtime = ordercreationtime;
	}
	public OrderEntity() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrdertotaltmount() {
		return ordertotaltmount;
	}

	public void setOrdertotaltmount(String ordertotaltmount) {
		this.ordertotaltmount = ordertotaltmount;
	}

	public List<OrderDetailEntity> getOrderDetailEntityList() {
		return orderDetailEntityList;
	}

	public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
		this.orderDetailEntityList = orderDetailEntityList;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public LocalDateTime getOrdercreationtime() {
		return ordercreationtime;
	}

	public void setOrdercreationtime(LocalDateTime ordercreationtime) {
		this.ordercreationtime = ordercreationtime;
	}
}
