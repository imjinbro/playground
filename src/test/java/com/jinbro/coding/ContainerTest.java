package com.jinbro.coding;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.CollectionUtils;

class ContainerTest {

	@ParameterizedTest
	@MethodSource("generateData")
	void ppblyaqne(List<Integer> numbers) throws Exception {
		if (CollectionUtils.isEmpty(numbers)) {
			return;
		}

		Queue<Integer> minusBlocks = new LinkedList<>(numbers.stream()
			.filter(b -> b < 0)
			.sorted()
			.collect(Collectors.toList())
		);

		Queue<Integer> plusBlocks = new LinkedList<>(numbers.stream()
			.filter(b -> b > 0)
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList())
		);

		List<Integer> containers = new ArrayList<>();
		int nextContainerValue = 0;

		while (!minusBlocks.isEmpty() && !plusBlocks.isEmpty()) {
			int minusBlock = Math.abs(minusBlocks.peek());
			int plusBlock = Math.abs(plusBlocks.peek());

			if (nextContainerValue == 0) {
				if (minusBlock > plusBlock) {
					nextContainerValue = minusBlocks.poll();
				}

				if (minusBlock < plusBlock) {
					nextContainerValue = plusBlocks.poll();
				}

				containers.add(nextContainerValue);
				continue;
			}

			if (minusBlock > plusBlock) {
				if (nextContainerValue > 0) {
					nextContainerValue = minusBlocks.poll();
					containers.add(nextContainerValue);
				} else {
					minusBlocks.poll();
				}
			}

			if (minusBlock < plusBlock) {
				if (nextContainerValue < 0) {
					nextContainerValue = plusBlocks.poll();
					containers.add(nextContainerValue);
				} else {
					plusBlocks.poll();
				}
			}
		}

		if (!plusBlocks.isEmpty() && nextContainerValue <= 0) {
			containers.add(plusBlocks.poll());
		}

		if (!minusBlocks.isEmpty() && nextContainerValue >= 0) {
			containers.add(minusBlocks.poll());
		}

		System.out.println(containers);
	}

	static Stream<Arguments> generateData() {
		return Stream.of(
			Arguments.of(Lists.newArrayList(11, -9, 2, 5, 18, 17, -15, 4)),
			Arguments.of(Lists.newArrayList(30, 22, -20, 12, 6, 5, -3, -1)),
			Arguments.of(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8)),
			Arguments.of(Lists.newArrayList(-1, -2, -3, -4, -5, -6, -7, 8)),
			Arguments.of(Lists.newArrayList(-1, -2, -3, -4, -5, -6, -7, 8, 9)),
			Arguments.of(Lists.newArrayList())
		);
	}
}