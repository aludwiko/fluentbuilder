/*
 * Created on 26-12-2012 09:41:58 by Andrzej Ludwikowski
 */

package fluentbuilder.example;



/** 
 * Fluent builder for Order
 * 
 */
public abstract class OrderBuilder extends AbstractOrderBuilder<OrderBuilder> {

	public OrderBuilder realized() {
		Order order = targetObject();
		order.orderRealized();
		// other methods
		return builder();
	}
}
