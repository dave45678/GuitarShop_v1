package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CUSTOMERS database table.
 * 
 */
@Entity
@Table(name="CUSTOMERS", schema="GUITARSHOP")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_ID")
	private long customerId;

	@Column(name="CUSTOMER_ADDRESS")
	private String customerAddress;

	@Column(name="CUSTOMER_CITY")
	private String customerCity;

	@Column(name="CUSTOMER_FAX")
	private String customerFax;

	@Column(name="CUSTOMER_FIRST_NAME")
	private String customerFirstName;

	@Column(name="CUSTOMER_LAST_NAME")
	private String customerLastName;

	@Column(name="CUSTOMER_PHONE")
	private String customerPhone;

	@Column(name="CUSTOMER_STATE")
	private String customerState;

	@Column(name="CUSTOMER_ZIP")
	private String customerZip;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<Order> orders;

	public Customer() {
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return this.customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerFax() {
		return this.customerFax;
	}

	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}

	public String getCustomerFirstName() {
		return this.customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return this.customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerState() {
		return this.customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerZip() {
		return this.customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}