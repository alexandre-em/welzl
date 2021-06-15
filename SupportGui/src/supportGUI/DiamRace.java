//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package supportGUI;

import algorithms.DefaultTeam;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class DiamRace {
	private static int width = 0;
	private static int height = 0;
	private static String title = "Diameter Racer";
	private static FramedDiamRace framedGUI;

	public DiamRace() {
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               DiamRace.framedGUI = new FramedDiamRace(DiamRace.width, DiamRace.height, DiamRace.title);
            }
        });
        synchronized(Variables.lock) {
            try {
                Variables.lock.wait();
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

		readFile(args[0]);
	}

	synchronized public static void readFile(String option) {
		ArrayList<Point> points = new ArrayList();
		Scanner sc = new Scanner(System.in);
//		int nbPoints = 256;
		int nbPoints = Integer.parseInt(sc.nextLine());
		int i=0;
		while (i<nbPoints) {
			String line = sc.nextLine();
			String s[] = line.split(" ");
			i++;
			points.add(new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}
		sc.close();
		framedGUI.drawPoints(points);
        synchronized(Variables.lock2) {
            Variables.lock2.notify();
        }
		// Evaluation temps
//		long t, t2, d, d2;
//		d = System.currentTimeMillis();
//		Circle c = (new DefaultTeam()).calculCercleMin(points, option);
//		t = System.currentTimeMillis() - d;
//		System.out.println(t); // Welzl Naif
		// Comparaison des resultats (rayon)

//		Circle cw = (new DefaultTeam()).calculCercleMin(points, "w");
//		Circle cn = (new DefaultTeam()).calculCercleMin(points, "n");
//		System.out.println("");
//		System.out.println(cw.getRadius()+" "+cn.getRadius()+" "+Math.abs( cn.getRadius() - cw.getRadius() )); // Welzl Naif difference 
//                    framedGUI.drawPoints(points);
	}
}
