package util;

import java.util.*;
import java.awt.*;

/**
 * 
 * class for coloring the staff & project summary
 * 
 * @author Dominic
 *
 */
public class CellColour {
	Random rand = new Random();
	public HashMap<Integer, Color> projectIdColor = new HashMap<Integer, Color>();
	ArrayList<Integer> projectIds = new ArrayList<Integer>();
	
	public CellColour() {
		
	}

	public void colourCell(ArrayList<Integer> projectIds) {
		this.projectIds = projectIds;
		boolean colorFound = true;
		boolean colorNotFound = false;
		Color randomColor = null;

		projectIdColor.put(0, Color.white);
		
		for (int i = 0; i < projectIds.size(); i++) {

			while (colorFound) {
				int R = (int) (Math.random() * 255);
				int G = (int) (Math.random() * 255);
				int B = (int) (Math.random() * 255);

				randomColor = new Color(R, G, B);

				if (projectIdColor.size() > 1) {

					for (int j = 0; j < projectIdColor.size() - 1; j++) {

						if (getBrightness(randomColor) <=200) {
							break;
						}

						int pR = projectIdColor.get(j).getRed();
						int pG = projectIdColor.get(j).getGreen();
						int pB = projectIdColor.get(j).getBlue();

						if (Math.abs(R - pR) > 20) {
							R = R+10;
						} else {
							R = R + 10;
						}
						if (Math.abs(G - pG) > 20) {
							G = G +10;
						} else {
							G = G + 10;
						}
						if (Math.abs(B - pB) > 20) {
							B = B+10;
						} else {
							B = B + 10;
						}

						if (R < 100 && G < 100 && B < 100) {
							R = (int) (Math.random() * 255);
							G = (int) (Math.random() * 255);
							B = (int) (Math.random() * 255);
						} else {
							colorFound = false;
						}


					}
				} else {
					//projectIdColor.put(projectIds.get(i), randomColor);
					projectIdColor.put(projectIds.get(i), randomColor);
					colorFound = false;
				}

			}
			
			//projectIdColor.put(i, randomColor);
			projectIdColor.put(projectIds.get(i), randomColor);
			colorFound = true;
		}

	}

	public HashMap<Integer, Color> getColor() {
		return projectIdColor;
	}

	public ArrayList<Integer> getprojectIds() {
		return this.projectIds;
	}

	private static int getBrightness(Color c) {
		return (int) Math.sqrt(c.getRed() * c.getRed() * .241 + c.getGreen()
				* c.getGreen() * .691 + c.getBlue() * c.getBlue() * .068);
	}
}
