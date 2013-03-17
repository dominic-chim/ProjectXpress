package util;

import java.util.*;
import java.awt.*;

public class CellColour {
	Random rand = new Random();
	HashMap<Integer, Color> projectIdColor = new HashMap<Integer, Color>();

	public CellColour() {
		// TODO Auto-generated constructor stub
	}

	public void colourCell(ArrayList<Integer> projectIds) {
		boolean colorFound = true;
		boolean colorNotFound = false;
		Color randomColor = null;

		for (int i=0;i<projectIds.size();i++) {

			while (colorFound) {
				int R = (int) (Math.random() * 256);
				int G = (int) (Math.random() * 256);
				int B = (int) (Math.random() * 256);

				randomColor = new Color(R, G, B);

				if (projectIdColor.size() > 1) {
					for (int j = 0; j < projectIds.size(); j++) {
						if (randomColor.equals(projectIdColor.get(j))) {
							break;
						} else if (j == projectIds.size() - 1) {
							colorFound = false;
						}
					}
				} else {
					projectIdColor.put(projectIds.get(i), randomColor);
					colorFound = false;
				}

			}
			// System.out.println(randomColor.getRGB());
			projectIdColor.put(projectIds.get(i), randomColor);
			//projectIdColor.
			colorFound = true;
		}
		
	}

	public HashMap<Integer, Color> getColor() {
		return projectIdColor;
	}
}
