package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {
	public double crossProduct(Point p, Point q, Point s, Point t) {
		return ((q.x - p.x) * (t.y - s.y) - (q.y - p.y) * (t.x - s.x));
	}

	public double vectorielProduct(Point i, Point j, Point k) {
		return (j.x - i.x) * (k.y - i.y) - (j.y - i.y) * (k.x - i.x);
	}

	/*
	 * Cas de base avec la taille de la liste des points = 2 grace a u milieu du
	 * segment des deux points, on determine la position du centre du cercle
	 */
	public Circle TwoPointMD(Point R, Point P) {
		double cx = (R.x + P.x) / 2;
		double cy = (R.y + P.y) / 2;
		double rayon = R.distance(P) / 2;
		Point p = new Point((int) cx, (int) cy);
		Circle D = new Circle(p, (int) rayon);
		return D;
	}

	/**
	 * Calcule le cercle circonscrit a partir du triangle forme par les points :
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return C, le cercle circonscrit des points {p1,p2,p3}
	 */
	public static Circle circleFromPoints(Point p1,Point p2,Point p3) {
		double offset = Math.pow(p2.x, 2) + Math.pow(p2.y, 2);
		double bc = (Math.pow(p1.x, 2) + Math.pow(p1.y, 2) - offset) / (double) 2;
		double cd = (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2)) / (double) 2;
		double det = (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p2.y);

		double idet = 1 / det;

		double centerx = (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
		double centery = (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
		double radius = Math.sqrt(Math.pow(p2.x - centerx, 2) + Math.pow(p2.y - centery, 2));

		return new Circle(new Point((int) centerx, (int) centery), (int) radius);
	}

	/**
	 * Verifie si le Point p est a l'interieur du cercle D en comparant la distance
	 * de p et de c, le centre du cercle D avec le rayon du cercle si la distance
	 * est inferieur au rayon alors il est a l'interieur du cercle sinon a
	 * l'exterieur
	 */
	public static boolean dansCercle(Circle D, Point p) {
		return (((p.x - D.getCenter().x) * (p.x - D.getCenter().x)
				+ (p.y - D.getCenter().y) * (p.y - D.getCenter().y)) < ((D.getRadius() + 1) * (D.getRadius() + 1)));
	}

	/**
	 * On verifie que tous les point p de points sont a l'interieur du cercle, si
	 * tous les points sont dans le cercle alors c'est un cercle couvrant
	 */
	public static boolean isEnclosingCircle(Circle c, ArrayList<Point> points) {
		for (Point p : points) {
			if (!dansCercle(c, p))
				return false;
		}
		return true;
	}

	/*
	 * Cas de base sur R
	 */
	public Circle closestDisk(ArrayList<Point> R) {
		if ((R.size() == 0))
			return new Circle(new Point(0, 0), 0);
		if (R.size() == 1)
			return new Circle(R.get(0), 0);
		if (R.size() == 2)
			return TwoPointMD(R.get(0), R.get(1));
		for (int i = 0; i < R.size(); i++) {
			for (int j = i + 1; j < R.size(); j++) {
				Circle c = TwoPointMD(R.get(i), R.get(j));
				if (isEnclosingCircle(c, R))
					return c;
			}
		}
		return circleFromPoints(R.get(0), R.get(1), R.get(2));
	}

	/**
	 * Fonction recursive qui permet de calculer le cercle min couvrant P
	 * @param P
	 * @param R
	 * @return D, le cercle minimum couvrant P
	 */
	public Circle smallestEnclosingDisk(ArrayList<Point> P, ArrayList<Point> R) {
		Circle D;
		if ((P.isEmpty()) || R.size() == 3) // Cas quand il n'y a plus de points dans P durant la recursive en cours, ou
											// qu'il y ait 3 points dans R
			D = closestDisk(R); // Cas de base
		else {
			ArrayList<Point> Pp = (ArrayList<Point>) P.clone(); // Creation d'un clone afin de ne pas affecter les P des
																// autres recursions
			int random = new Random().nextInt(P.size());
			Point p = Pp.remove(random); // On enleve le point P de P prime afin de ne pas affecter P et respecter
											// l'algorithme
			D = smallestEnclosingDisk(Pp, R);
			if (D != null && !dansCercle(D, p)) { // si D n'est pas defini ou si p n'est pas dans le cercle c'est a dire
													// qu'il est dans la bordure du cercle
				ArrayList<Point> Rp = (ArrayList<Point>) R.clone();
				Rp.add(p); // on ajoute donc p dans R prime afin de ne pas affecter R
				D = smallestEnclosingDisk(Pp, Rp);
			}
		}
		return D;
	}

	// calculCercleMin: ArrayList<Point> --> Circle
	// renvoie un cercle couvrant tout point de la liste, de rayon minimum.
	public Circle calculCercleMin(ArrayList<Point> points, String option) {
		if (option.charAt(0) == 'n')
			return naif(points);
		return welzl(points);
	}

	// Calcul miniDisk of points
	public Circle welzl(ArrayList<Point> points) {
//		System.out.println("welzl");
		if (points.isEmpty()) {
			return null;
		}
		return smallestEnclosingDisk(points, new ArrayList<Point>());
	}

	public Circle naif(ArrayList<Point> inputPoints) {
//		 System.out.println("Naif");
		ArrayList<Point> points = (ArrayList<Point>) inputPoints.clone();
		if (points.size() < 1)
			return null;
		double cX, cY, cRadius, cRadiusSquared;
		for (Point p : points) {
			for (Point q : points) {
				cX = .5 * (p.x + q.x);
				cY = .5 * (p.y + q.y);
				cRadiusSquared = 0.25 * ((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
				boolean allHit = true;
				for (Point s : points)
					if ((s.x - cX) * (s.x - cX) + (s.y - cY) * (s.y - cY) > cRadiusSquared) {
						allHit = false;
						break;
					}
				if (allHit)
					return new Circle(new Point((int) cX, (int) cY), (int) Math.sqrt(cRadiusSquared));
			}
		}
		double resX = 0;
		double resY = 0;
		double resRadiusSquared = Double.MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					Point p = points.get(i);
					Point q = points.get(j);
					Point r = points.get(k);
					// si les trois sont colineaires on passe
					if ((q.x - p.x) * (r.y - p.y) - (q.y - p.y) * (r.x - p.x) == 0)
						continue;
					// si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les
					// echange
					if ((p.y == q.y) || (p.y == r.y)) {
						if (p.y == q.y) {
							p = points.get(k); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							r = points.get(i); // parce que les trois points sont non-colineaires
						} else {
							p = points.get(j); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							q = points.get(i); // parce que les trois points sont non-colineaires
						}
					}
					// on cherche les coordonnees du cercle circonscrit du triangle pqr
					// soit m=(p+q)/2 et n=(p+r)/2
					double mX = .5 * (p.x + q.x);
					double mY = .5 * (p.y + q.y);
					double nX = .5 * (p.x + r.x);
					double nY = .5 * (p.y + r.y);
					// soit y=alpha1*x+beta1 l'equation de la droite passant par m et
					// perpendiculaire a la droite (pq)
					// soit y=alpha2*x+beta2 l'equation de la droite passant par n et
					// perpendiculaire a la droite (pr)
					double alpha1 = (q.x - p.x) / (double) (p.y - q.y);
					double beta1 = mY - alpha1 * mX;
					double alpha2 = (r.x - p.x) / (double) (p.y - r.y);
					double beta2 = nY - alpha2 * nX;
					// le centre c du cercle est alors le point d'intersection des deux droites
					// ci-dessus
					cX = (beta2 - beta1) / (double) (alpha1 - alpha2);
					cY = alpha1 * cX + beta1;
					cRadiusSquared = (p.x - cX) * (p.x - cX) + (p.y - cY) * (p.y - cY);
					if (cRadiusSquared >= resRadiusSquared)
						continue;
					boolean allHit = true;
					for (Point s : points)
						if ((s.x - cX) * (s.x - cX) + (s.y - cY) * (s.y - cY) > cRadiusSquared) {
							allHit = false;
							break;
						}
					if (allHit) {
						resX = cX;
						resY = cY;
						resRadiusSquared = cRadiusSquared;
					}
				}
			}
		}
		return new Circle(new Point((int) resX, (int) resY), (int) Math.sqrt(resRadiusSquared));
	}

	public Line calculDiametre(ArrayList<Point> points) {
		if (points.size() < 3) {
			return null;
		}

		Point p = points.get(0);
		Point q = points.get(1);

		/*******************
		 * PARTIE A ECRIRE *
		 *******************/
		return new Line(p, q);
	}
	
}
