package util;

import java.util.Arrays;
import java.util.Scanner;


public class ObjectArrayList {
	
	
	
	
	final int CAPACITY = 4;
	int pos = 0;
	Object[] list = new Object[CAPACITY];

	
	
	
	public boolean isFull() {
		return pos == CAPACITY ;
		
	}
	
	public boolean isEmpty() {
		return pos == 0;
	}
	
	public void add(Object obj) {
		
		add(pos, obj);
	}
	
	public void add(int index, Object obj) {
		if (pos == list.length) {
			list = Arrays.copyOf(list, pos * 2);
		}
		
		
		int n = pos - index;
		if (n > 0) {
			System.arraycopy(list, index, list, index + 1, n);
		}
		
		list[index] = obj;
		pos++;
		
	}
	
	
	
	public Object remove(int index) {
		
		Object val = list[index];
		
		int num = pos - (index + 1);
		if (num > 0) {
			System.arraycopy(list, index + 1, list, index, num);
		}
		// 마지막 요소
		list[--pos] = null;
		
		return val;
	}
	
	
	public Object get(int index) {
		if (index < 0 || index > pos) {
			System.out.println("잘못된 위치입니다.\n");
			return null;
		}
		return list[index];
		
	}
	public int size() {
		return pos;
	}
	
	public void clear() {
		for (int i = 0; i < pos; i++) {
			list[i] = null;
		}
		pos = 0;
	}
	
	
	
	

	

}
