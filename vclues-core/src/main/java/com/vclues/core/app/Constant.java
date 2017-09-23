package com.vclues.core.app;

public class Constant {
	
	public final static String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
	
	public static class USER_TYPE {
		public Integer GUEST = 0;
		public Integer LIMITED = 1 << 0; // 1 - registered online
		public Integer PAID = 1 << 1; // 2 - paid for access code of individual games - games pre-created during email confirmation on purchase of access code based on what game is assigned to the access code
		
		public Integer PAID2 = 1 << 2; // 4 - paid for unlimited access and can choose game and invite		
		public Integer career = 1 << 3; // 8
		public Integer travel = 1 << 4; // 16
		public Integer education = 1 << 5; // 32
		public Integer fashion = 1 << 6; // 64
		public Integer ethics = 1 << 7; // 128
		public Integer religion = 1 << 8; // 256
		public Integer power = 1 << 9; // 512
		public Integer wealth = 1 << 10; // 1024
		public Integer drive = 1 << 11; // 2048
		public Integer beauty = 1 << 12; // 4096
		public Integer family = 1 << 13; // 8192
		public Integer fitness = 1 << 14; // 16384
		public Integer food = 1 << 15; // 32768
		public Integer books = 1 << 16; // 65536
		public Integer artistry = 1 << 17; // 131072
	}
	
	
	public final static int CURRENT = 0;
	public final static int PAST = 1;
	
	public final static String SEND_REFUND = "SEND_REFUND";
	public final static String REJECT_REFUND = "REJECT_REFUND";
	public final static String READ_PERMISSION = "READ_PERMISSION";
	public final static String READ_PARENT_ACCOUNT = "READ_PARENT_ACCOUNT";
	public final static String READ_GROUP = "READ_GROUP";
	public final static String READ_DESCRIPTOR = "READ_DESCRIPTOR";
	public final static String READ_CASES = "READ_CASES";
	public final static String EDIT_PERMISSION = "EDIT_PERMISSION";
	public final static String EDIT_PARENT_ACCOUNT = "EDIT_PARENT_ACCOUNT";
	public final static String EDIT_GROUP = "EDIT_GROUP";
	public final static String EDIT_DESCRIPTOR = "EDIT_DESCRIPTOR";
	public final static String EDIT_CASES = "EDIT_CASES";
	public final static String DELETE_PERMISSION = "DELETE_PERMISSION";
	public final static String DELETE_PARENT_ACCOUNT = "DELETE_PARENT_ACCOUNT";
	public final static String DELETE_GROUP = "DELETE_GROUP";
	public final static String DELETE_DESCRIPTOR = "DELETE_DESCRIPTOR";
	public final static String DELETE_CASES = "DELETE_CASES";
	public final static String DELETE_ACCOUNT = "DELETE_ACCOUNT";
	public final static String ADD_PERMISSION = "ADD_PERMISSION";
	public final static String ADD_GROUP = "ADD_GROUP";
	public final static String ADD_DESCRIPTOR = "ADD_DESCRIPTOR";
	public final static String ADD_CASES = "ADD_CASES";
	public final static String ADD_ACCOUNT = "ADD_ACCOUNT";

}
