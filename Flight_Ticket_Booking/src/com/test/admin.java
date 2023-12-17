package com.test;

public class admin {
	
		private int admin_Id;
		private String admin_name;
		private String admin_password;
		public admin(int admin_Id, String admin_name, String admin_password) {
			super();
			this.admin_Id = admin_Id;
			this.admin_name = admin_name;
			this.admin_password = admin_password;
		}
		public admin() {
			
		}
		public int getAdmin_Id() {
			return admin_Id;
		}
		public void setAdmin_Id(int admin_Id) {
			this.admin_Id = admin_Id;
		}
		public String getAdmin_name() {
			return admin_name;
		}
		public void setAdmin_name(String admin_name) {
			this.admin_name = admin_name;
		}
		public String getAdmin_password() {
			return admin_password;
		}
		public void setAdmin_password(String admin_password) {
			this.admin_password = admin_password;
		}
		
		
}