package com.sort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class Test {
	static Map<String, List<String>> maps=new HashMap<String, List<String>>();
	public static void main(String[] args) throws Exception {	
	String[] arr={"a","b","c","d","e","f"};
	sort(Arrays.asList(arr),new ArrayList<String>());
	}
	public static Map<String, List<String>> sort(List<String> datas, List<String> target) {
		 List<String> lists=new ArrayList<String>();
		if (target.size() ==6) {
			int i=0;
			for (String obj : target)
			{
				lists.add(obj);
			}
			i++;
			System.out.println(lists+"==============="+i);
			maps.put(UUID.randomUUID().toString(), lists);
		}
		for (int i = 0; i < datas.size(); i++) {
			List<String> newDatas = new ArrayList<String>(datas);
			List<String> newTarget = new ArrayList<String> (target);
			newTarget.add(newDatas.get(i));
			newDatas.remove(i);
			sort(newDatas, newTarget);
		}
		return maps;
	}
}
