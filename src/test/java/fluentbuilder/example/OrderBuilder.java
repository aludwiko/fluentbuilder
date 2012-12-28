/* 
 * Created on 26-12-2012 09:41:58 by Andrzej Ludwikowski
 */

package fluentbuilder.example;

import java.util.Date;
import java.util.List;

import fluentbuilder.proxy.AbstractBuilder;
import fluentbuilder.proxy.AbstractBuilderFactory;


/** 
 * Fluent builder for Order
 * 
 */
public abstract class OrderBuilder extends AbstractBuilder<Order, OrderBuilder> {

	public abstract OrderBuilder withItems(List<OrderItem> items);
	public abstract OrderBuilder withCreateDate(Date createDate);
	public abstract OrderBuilder withRealized(boolean realized);

	public OrderBuilder realized() {
		Order order = targetObject();
		order.orderRealized();
		// other methods
		return builder();
	}

	public static OrderBuilder create() {
		return AbstractBuilderFactory.createImplementation(OrderBuilder.class);
	}
}
