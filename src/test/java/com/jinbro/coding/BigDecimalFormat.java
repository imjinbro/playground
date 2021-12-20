package com.jinbro.coding;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BigDecimalFormat {

	@Test
	@DisplayName("지수표현법 없애기 - Long 컨버팅 시 지수표현법으로 표기되는 경우 BigDecimal Wrapper 클래스 사용하기")
	void bzhsrvlbz() throws Exception {
		//given
		String numStr = "1.300132E7";

		//when
		BigDecimal num = new BigDecimal(Double.valueOf(numStr));

		//then
		assertEquals(13001320, num.intValue());
	}
}