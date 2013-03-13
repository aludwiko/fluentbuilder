/* 
 * Created on 25-12-2012 11:41:37 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import java.util.Date;
import java.util.List;



public class Order {

	private List<OrderItem> items;
	private Date createDate;
	private boolean realized;


	public boolean isRealized() {
		return realized;
	}

	public void orderRealized() {
		// very complex implementation
		realized = true;
	}
}
