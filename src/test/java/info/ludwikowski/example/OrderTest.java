/* 
 * Created on 25-12-2012 11:44:17 by Andrzej Ludwikowski
 */

package info.ludwikowski.example;

import static org.fest.assertions.api.Assertions.assertThat;
import info.ludwikowski.generator.AbstractBuilderGenerator;

import java.util.Date;

import org.junit.Test;


public class OrderTest {

	@Test
	public void should() {

		// given

		// when
		Order realizedOrder = OrderBuilder.create()
											.realized()
											.withCreateDate(new Date())
											.build();

		// then
		assertThat(realizedOrder.isRealized()).isTrue();

		AbstractBuilderGenerator.forClass(Order.class)
								.withIndefiniteArticles(false)
								.withVarargsForCollections(false)
								// join two builders in one class
								// .printSingleBuilder();
								.printBuilders();
	}
}
