package de.max.SPAddressDB;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;

public class MapDBTests {


	/**
	 * tests weather the vals that type written are also read
	 */
	@Test
	public void writeInteger() {
		String path = "." + File.separator + "tempDB.txt";
		Database<String, Integer> db = new MapDB<>(path);
		Map<String, Integer> map = new LinkedHashMap<>();
		int runs = 100;
		Random rand = new Random();
		for (int i = 0; i < runs; i++) {
			map.put(rand.nextInt() + "", rand.nextInt());
			db.update(map);
		}
		assertArrayEquals(map.values().toArray(new Integer[1]), db.get().values().toArray(new Integer[1]));
		File rem = new File(path);
		rem.delete();
	}

	/**
	 * tests for invalid paths
	 */
	@Test
	public void invalidPathGet1() {
		String path = "";
		Path p = Paths.get("5535235235/");
		Database<String, String> db = new MapDB<>(path);
		assertNull(db.get(path));
		File rem = new File(path);
		rem.delete();
	}
	@Test
	public void invalidPathGet2() {
		String path = "/hom:e/max/";
		Database<String, String> db = new MapDB<>(path);
		assertNull(db.get(path));
		File rem = new File(path);
		rem.delete();
	}
	@Test
	public void validPathGet() {
		String path = "/home/max/";
		Database<String, String> db = new MapDB<>(path);
		assertNotNull(db.get(path));
		File rem = new File(path);
		rem.delete();
	}
	@Test
	public void validPathGet2() {
		String path = "/home/max/Desktop";
		Database<String, String> db = new MapDB<>(path);
		assertNotNull(db.get(path));
		File rem = new File(path);
		rem.delete();
	}
	@Test
	public void getPermissions() {
		String path = "/opt/Krita/";
		Database<String, String> db = new MapDB<>(path);
		assertNull(db.get());
		File rem = new File(path);
		rem.delete();
	}
	@Test
	public void getPermissions2() {
		String path = "/";
		Database<String, String> db = new MapDB<>(path);
		assertNull(db.get());
		File rem = new File(path);
		rem.delete();
	}
}