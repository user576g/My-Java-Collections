package ua.user576.collections.arraylist;

import java.util.Iterator;

public class ArrayTests {

	public static void main(String[] args) {
		test1();
		// ...
	}

	private static void test1() {

		Array array = new ArrayImpl();
		array.add("A");
		array.add("B");
		array.add("C");

		System.out.println(array);
		System.out.println(array.size());

		array.clear();
		System.out.println(array);
		System.out.println(array.size());

		array.add("A");
		array.add("B");
		array.add("C");

		System.out.println(array);
		System.out.println(array.size());

		/* an output must be as the following:
		*************************************
		[A, B, C]
		3
		[]
		0
		[A, B, C]
		3
		*************************************
		*/
	}
}