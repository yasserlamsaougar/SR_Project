package shared;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

public class MapData implements Serializable {

	private int mapWidth;
	private int mapHeight;
	private List<Point> targetArray;
	
	public MapData(int mapWidth, int mapHeight, List<Point> tagetArray) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.targetArray = tagetArray;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	protected void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	protected void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public List<Point> getTargetArray() {
		return targetArray;
	}

	protected void setTargetArray(List<Point> targetArray) {
		this.targetArray = targetArray;
	}
	
		
}
