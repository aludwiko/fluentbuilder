/* 
 * Created on 10-03-2013 17:01:38 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import info.ludwikowski.common.AbstractBuilder;

import java.util.Date;
import java.util.List;



public abstract class AbstractOrderBuilder<B> extends AbstractBuilder<Order, B> {

	public abstract AbstractOrderBuilder<B> withItems(List<OrderItem> items);

	public abstract AbstractOrderBuilder<B> withCreateDate(Date createDate);

	public abstract AbstractOrderBuilder<B> withRealized(boolean realized);

}
