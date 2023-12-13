package com.iot.back.models.entities.user;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCart {


	
	private String product_id;
	@Schema(description = "product name", example = "Skol beats")
	private String name;
	@Schema(description = "product price", example = "Skol beats")
	private Double  price;
	@Schema(description = "product quantity in storage", example = "5")
	private Integer quantity;
	@Schema(description = "product image url", example = "5")
	private String image;
	
	public ProductCart(String id, String name, Double price, Integer quantity, String image) {
		this.product_id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
	}

	public ProductCart() {
	}

	public String getId() {
		return product_id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getImage() {
		return image;
	}

	public void setId(String id) {
		this.product_id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCart other = (ProductCart) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ProductCart [product_id=" + product_id + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + ", image=" + image + "]";
	}
	
	
	

	
	
}
