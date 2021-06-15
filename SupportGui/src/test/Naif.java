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

class Naif {

	/**
	 * Test du Cas de Base ou |P| = 0
	 */
	@Test
	void testNaifVide() {
		ArrayList<Point> points = new ArrayList<Point>();
		Circle c = new DefaultTeam().naif(points);
		assertNull(c);
	}

	/**
	 * Test du Cas de Base ou |P| = 1
	 */
	@Test
	void testNaif1Point() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		Circle c = new DefaultTeam().naif(points);
		assertTrue(c.getCenter().equals(points.get(0)));
		assertTrue(c.getRadius() == 0);
	}

	/**
	 * Test du Cas de Base ou |P| = 2
	 */
	@Test
	void testNaif2Points() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		points.add(new Point(500, 500));
		Circle c = new DefaultTeam().naif(points);
		double dist = Math.sqrt((points.get(0).x - points.get(1).x) * (points.get(0).x - points.get(1).x)
				+ (points.get(0).y - points.get(1).y) * (points.get(0).y - points.get(1).y));
		assertTrue((int) dist / 2 == c.getRadius());
	}

	/**
	 * Test du Cas de Base ou |P| = 3
	 */
	@Test
	void testNaifEasy() {
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(200, 200));
		points.add(new Point(400, 400));
		points.add(new Point(300, 300));
		Circle c = new DefaultTeam().naif(points);
//		System.out.println(c.getRadius());
		assertTrue(DefaultTeam.isEnclosingCircle(c, points));

	}
/**
 * Test pour un ensemble de 256 points, si l'algorithme Naif retourne bien un cercle minimum couvrant tous les points
 * l'algo ne fonctionne cependant pas pour toutes les instances
 */
	@Test
	void testNaifMedium() {
		BufferedReader input;
		ArrayList<Point> points = new ArrayList<>();
		String line;
		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream("./samples/test-1000.points")));
			try {
				while ((line = input.readLine()) != null) {
					String[] coordinates = line.split("\\s+");
					points.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Circle c = new DefaultTeam().naif(points);
		assertTrue(DefaultTeam.isEnclosingCircle(c, points));
	}
	
	/**
	 * Test pour les 1664 instances de la base de test de Vemouras, chacune des instances contiennent 256 points
	 */
//	@Test
//	void testNaif1664() {
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
//			if(DefaultTeam.isEnclosingCircle(c, points))
//				cpt++;
//		}
//		System.out.println(cpt);
//		assertTrue(cpt>=(int)(832));
//	}
}
