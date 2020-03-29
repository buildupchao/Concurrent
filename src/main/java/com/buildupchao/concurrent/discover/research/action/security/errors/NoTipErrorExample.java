package com.buildupchao.concurrent.discover.research.action.security.errors;

/**
 * Stack over flow problem.
 * @see
 *
 * @author buildupchao
 * @date 2018/05/20
 * @since
 */
public class NoTipErrorExample {
	
	public static void main(String[] args) {
		int v1 = 1_073_741_827;
		int v2 = 1_431_655_768;
		System.out.println("v1=" + v1);
		System.out.println("v2=" + v2);
		
		int average = (v1 + v2) / 2;
		System.out.println("average of " + v1 + ", " + v2 + " is " + average);
	}
}