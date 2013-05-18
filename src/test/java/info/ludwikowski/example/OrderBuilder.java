/*
 * Created on 26-12-2012 09:41:58 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import info.ludwikowski.generator.proxy.AbstractBuilderFactory;



/** 
 * Fluent builder for Order
 * 
 */
public abstract class OrderBuilder extends AbstractOrderBuilder<OrderBuilder> {

	public static OrderBuilder create() {
		return AbstractBuilderFactory.createImplementation(OrderBuilder.class);
	}

	public OrderBuilder realized() {
		Order order = targetObject();
		order.orderRealized();
		// other methods
		return builder();
	}
}
