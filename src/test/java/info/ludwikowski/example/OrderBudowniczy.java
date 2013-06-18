/* 
 * Created on 19-03-2013 17:42:26 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import info.ludwikowski.common.AbstractBuilder;
import info.ludwikowski.common.AbstractBuilderFactory;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;


public abstract class OrderBudowniczy extends AbstractBuilder<Order, OrderBudowniczy>{

	public static OrderBudowniczy utworz(){
		return AbstractBuilderFactory.createImplementation(OrderBudowniczy.class);
	}

	public static final String getPrefix() {
		return "z";
	}

	public Order buduj() {
		return build();
	}

	public abstract OrderBudowniczy zItems(List<OrderItem> items);
	public abstract OrderBudowniczy zCreateDate(Date createDate);
	public abstract OrderBudowniczy zRealized(boolean realized);

	public OrderBudowniczy zItems(OrderItem... items){
		return zItems(Lists.newArrayList(items));
	}

	public OrderBudowniczy zrealizowane() {
		return zRealized(true);
	}
}

