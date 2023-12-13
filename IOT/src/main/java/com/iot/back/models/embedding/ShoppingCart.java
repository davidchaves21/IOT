package com.iot.back.models.embedding;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "User login information")
@Embeddable
public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "id product", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	private String productId;
	@Schema(description = "quantity product", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	private Integer quantity;
	
	public ShoppingCart(String productId, Integer quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}
			
	public ShoppingCart() {
	}

	public String getProductId() {
		return productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "ShoppingCart [productId=" + productId + ", quantity=" + quantity + "]";
	}

	

}
