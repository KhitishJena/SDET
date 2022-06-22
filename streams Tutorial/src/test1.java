import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 {

	@Test
	public void regular() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Aakash");
		names.add("Debasish");
		names.add("Abhinav");
		names.add("Satish");
		names.add("Adam");
		names.add("Debadutt");

		int count = 0;

		for (int i = 0; i < names.size(); i++) {

			String data = names.get(i);
			if (data.startsWith("D")) {
				count++;
			}
		}
		System.out.println("The normal count is " + count);
	}

	@Test
	public void streamsFilter() {

		ArrayList<String> names = new ArrayList<String>();
		names.add("Aakash");
		names.add("Debasish");
		names.add("Abhinav");
		names.add("Satish");
		names.add("Adam");

		Long c = names.stream().filter(s -> s.startsWith("A")).count();
		System.out.println("The stream count is " + c);

		Long d = Stream.of("Aaaab", "Asdfb", "Serfd", "Serwb", "Qwerty").filter(s -> {
			s.startsWith("A");
			// s.endsWith("b");
			return true;
		}).count();

		System.out.println("The streamOf type count is " + d);

		names.stream().filter(s -> s.length() > 6).forEach(s -> System.out.println(s));

	}

	@Test
	public void streamMap() {

		Stream.of("Aaaab", "Asdfb", "Serfd", "Serwb", "Qwerty").map(s -> s.toUpperCase())
				.forEach(s -> System.out.println("All upper Case are " + s));

		Stream.of("Aaaab", "Asdfb", "Serfd", "Serwb", "Qwerty").filter(s -> s.endsWith("b")).map(s -> s.toUpperCase())
				.forEach(s -> System.out.println("The upper Case are " + s));

		List<String> names = Arrays.asList("Allok", "Aakash", "Debasish", "Abhinav", "Satish", "Adam");
		names.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase())
				.forEach(s -> System.out.println("The NAMES upper Case are " + s));

		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Jena");
		titles.add("Biswal");
		titles.add("Nayak");
		titles.add("Patra");
		titles.add("Das");

		// Merging two different list names and title...once the stream is operated upon
		// cannot be used further
		Stream<String> fullName = Stream.concat(names.stream(), titles.stream());
		// fullName.sorted().forEach(s-> System.out.println(s));
		boolean flag = fullName.anyMatch(s -> s.equalsIgnoreCase("yena"));
		System.out.println("^^^^^^FLAG*******" + flag);
		Assert.assertTrue(flag, "Match not found **********");

	}

	@Test
	public void streamCollect() {

		List<String> ls = Stream.of("Aaaab", "Asdfb", "Serfd", "Serwb", "Qwerty").map(s -> s.toUpperCase())
				.collect(Collectors.toList());
		System.out.println("&&&&&&&&&&&&&&& " + ls.get(0));
		System.out.println("&&&&&&&&&&&&&&& " + ls.contains("ASDFB"));
		
		//print the unique number and sort them
		List<Integer> integers = Arrays.asList(2,3,4,5,1,1,2,3,7,8,9,3,6,5,6,0,9,8);
		integers.stream().distinct().sorted().forEach(s->System.out.println(s));
	}
}
