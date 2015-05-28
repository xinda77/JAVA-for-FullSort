package com.sort;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.util.DBUtil;

public class ImportDateFromMongoDB {
	public Date QueryBackwardDate(Date date, int i) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_MONTH, 1);
		Date startDate=cd.getTime();
		cd.add(Calendar.MONTH, -1);
		Date afterDate = cd.getTime();
		List<Long> list = new ArrayList<Long>();
		Date oldDay = null;
		MongoDatabase db = DBUtil.getClient().getDatabase("LFDATA");
		FindIterable<Document> iterable = db
				.getCollection("T_BASE_TRADE_DAY")
				.find(and(lt("TradingDate", startDate), gt("TradingDate", afterDate)));
		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				if (document.getString("IfTradingDay").equals("1")) {
					list.add(document.getDate("TradingDate").getTime());
				}
			}
		});
		Collections.sort(list, new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return -o1.compareTo(o2);
			}
		});
		if(date.getTime()==list.get(0)){
			if(i==1){
				oldDay=date;
			}
			else {
				oldDay=new Date(list.get(1));
			}
		}
		else {
			oldDay=new Date(list.get(0));
		}
		
		return oldDay;
	}
	
}
