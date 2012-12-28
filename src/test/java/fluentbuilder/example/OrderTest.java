/* 
 * Created on 25-12-2012 11:44:17 by Andrzej Ludwikowski
 */

package fluentbuilder.example;

import static org.fest.assertions.api.Assertions.assertThat;

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
	}
}
