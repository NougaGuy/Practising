package com.nouga.LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nouga Date : 2017/8/9 Declaration : All Rights Reserved !
 */
public class TwoSum {

	/**
	 * Give an array of integers , return indices of the two numbers such that
	 * they add up to a specific target .
	 * You may assume that each input would had exactly one solution
	 * and you may not use the same element twice . 
	 */
	public static int[] twoSum (int[] sums , int target ){
		for (int i = 0; i < sums.length; i++) {
			for (int j2 = i+1 ; j2 < sums.length; j2++) {
				if(sums[i]+sums[j2]==target){
					return new int[]{i,j2};
				}
			}
		}
		throw new IllegalArgumentException("No Two Sum Solution");
	}
	
	public static int[] twoSum2(int[] sums , int target) {
		Map<Integer,Integer> m1 = new HashMap();
		for (int i = 0; i < sums.length; i++) {
			int complement = target - sums[i];
			if(m1.containsKey(complement)){
				return new int[]{m1.get(complement),i};
			}
			m1.put(sums[i], i);
		}
		throw new IllegalArgumentException("No Two Sum Solution");
	}
	public static void main(String[] args) {
		int[] sums = {2,7,11,15};
		int[] twoSum = twoSum(sums,9);
		System.out.println(twoSum.length);
	}
}
