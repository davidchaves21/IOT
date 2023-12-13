package com.iot.back.models.entities.user;

public class AddCart {
		
		private String product_id;

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public AddCart(String product_id) {
			this.product_id = product_id;
		}

		public AddCart() {
		}

		@Override
		public String toString() {
			return "AddCart [product_id=" + product_id + "]";
		}
		
		
}
