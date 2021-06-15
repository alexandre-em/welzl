package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import algorithms.DefaultTeam;
import supportGUI.Circle;

class WelzlTest {

	/**
	 * Test du Cas de Base ou |P| = 0 + comparaison avec l'algorithme naif
	 */
	@Test
	void testWelzlVide() {
		ArrayList<Point> points = new ArrayList<Point>();
		Circle c = new DefaultTeam().welzl(points);
		assertNull(c);
		Circle c2 = new DefaultTeam().naif(points);
		assertNull(c2);
	}

	/**
	 * Test du Cas de Base ou |P| = 1 + comparaison avec l'algorithme naif
	 */
	@Test
	void testWelzl1Point() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		Circle c = new DefaultTeam().welzl(points);
		assertTrue(c.getCenter().equals(points.get(0)));
		assertTrue(c.getRadius() == 0);
		Circle c2 = new DefaultTeam().naif(points);
		assertTrue(c.getRadius()==c2.getRadius());
	}

	/**
	 * Test du Cas de Base ou |P| = 2 + comparaison avec l'algorithme naif
	 */
	@Test
	void testWelzl2Points() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		points.add(new Point(500, 500));
		Circle c = new DefaultTeam().welzl(points);
		double dist = Math.sqrt((points.get(0).x - points.get(1).x) * (points.get(0).x - points.get(1).x)
				+ (points.get(0).y - points.get(1).y) * (points.get(0).y - points.get(1).y));
		assertTrue((int) dist / 2 == c.getRadius());
		Circle c2 = new DefaultTeam().naif(points);
		assertTrue(c.getRadius()==c2.getRadius());
	}

	/**
	 * Test du Cas de Base ou |P| = 3 + comparaison avec l'algorithme naif
	 */
	@Test
	void testWelzlEasy() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		points.add(new Point(400, 400));
		points.add(new Point(300, 300));
		Circle c = new DefaultTeam().welzl(points);
		assertTrue(DefaultTeam.isEnclosingCircle(c, points));
		Circle c2 = new DefaultTeam().naif(points);
		assertTrue(c.getRadius()==c2.getRadius());
	}

	/*
	 * Test de 1664 instances de test, on verifie pour chaque instance si les rayons des 2 cercles obtenus sont de tailles egales
	 */
//	@Test
//	void testWelzl1664() {
//		BufferedReader input;
//		String line;
//		int cpt = 0;
//		for(int i=1;i<1665;i++) {
//			ArrayList<Point> points = new ArrayList<>();
//			try {
//				input = new BufferedReader(new InputStreamReader(new FileInputStream("./samples/test-"+i+".points")));
//				try {
//					while ((line = input.readLine()) != null) {
//						String[] coordinates = line.split("\\s+");
//						points.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
//					}
//				} catch (NumberFormatException | IOException e) {
//					e.printStackTrace();
//				} finally {
//					try {
//						input.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//			Circle c = new DefaultTeam().welzl(points);
//			Circle c2 = new DefaultTeam().naif(points);
//			if(c.getRadius()==c2.getRadius())
//				cpt++;
//		}
//		System.out.println(cpt);
//		assertTrue(cpt>=800);
//	}
}
