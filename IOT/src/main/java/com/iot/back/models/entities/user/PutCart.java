package com.iot.back.models.entities.user;

public class PutCart {
		
		private String product_id;

		private String action;

		public PutCart(String product_id, String action) {
			this.product_id = product_id;
			this.action = action;
		}

		public PutCart() {
		}

		public String getProduct_id() {
			return product_id;
		}

		public String getAction() {
			return action;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public void setAction(String action) {
			this.action = action;
		}

		@Override
		public String toString() {
			return "PutCart [product_id=" + product_id + ", action=" + action + "]";
		}
		
		
}
