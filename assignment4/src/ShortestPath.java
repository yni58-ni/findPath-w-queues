/**
 * This is a class to find the shortest path to a destination
 * 
 * @author Nicole Ni
 *
 */
public class ShortestPath {
	CityMap cityMap;

	/**
	 * constructor
	 */
	public ShortestPath(CityMap theMap) {
		cityMap = theMap;
	}

	/**
	 * look for a path with the minimum number of map cell from the starting cell to
	 * the destination cell
	 */
	public void findShortestPath() {
		int pathNum = 0;
		MapCell theSmall;
		OrderedCircularArray<MapCell> newList = new OrderedCircularArray<MapCell>();

		MapCell lastCell = cityMap.getStart(); // get the starting cell
		newList.insert(lastCell, 0); // insert the first CellData item
		lastCell.markInList();

		do {
			try {
				theSmall = newList.getSmallest(); // S find the smallest in the list
				theSmall.markOutList();
				if (theSmall.isDestination()) {
					break;
				}
			} catch (EmptyListException e) {
				System.out.println("There is no path from the starting point to the destination.");
				return;
			}

			for (int i = 0; i <= 3; i++) {
				lastCell = nextCell(theSmall); // C find the next cell (neighbourCell)
				if (lastCell == null) {
					continue;
				} else {
					int newDistance = 1 + theSmall.getDistanceToStart(); // D

					if (lastCell.getDistanceToStart() > newDistance) {
						lastCell.setDistanceToStart(newDistance);
						lastCell.setPredecessor(theSmall);
					}
					int newDistance2 = lastCell.getDistanceToStart(); // P
					if (lastCell.isMarkedInList() && newDistance2 < newList.getValue(lastCell)) {
						newList.changeValue(lastCell, newDistance2);
					} else if (!lastCell.isMarkedInList()) {
						newList.insert(lastCell, newDistance2);
						lastCell.markInList();
					}
				}
			}

		} while (!theSmall.isDestination());
		pathNum = theSmall.getDistanceToStart() + 1;
		System.out.println("It took " + pathNum + " cells from the starting point to the destionation.");
	}

	/**
	 * return the first unmarked neighboring map cell that can be used to continue
	 * the path from the current one
	 */
	private MapCell nextCell(MapCell cell) {
		MapCell currentCell = cell;
		MapCell theNeighbour;
		for (int i = 0; i <= 3; i++) {
			theNeighbour = currentCell.getNeighbour(i);
			if (theNeighbour == null) {
				continue;
			}
			// check if the neighbour cell is a intersection
			if (!theNeighbour.isMarked() && !theNeighbour.equals("null")) {
				if (theNeighbour.isIntersection() && currentCell.isIntersection()
						|| theNeighbour.isIntersection() && currentCell.isStart()
						|| theNeighbour.isIntersection() && currentCell.isSouthRoad() && i == 2
						|| theNeighbour.isIntersection() && currentCell.isWestRoad() && i == 3
						|| theNeighbour.isIntersection() && currentCell.isNorthRoad() && i == 0
						|| theNeighbour.isIntersection() && currentCell.isEastRoad() && i == 1) {

					return theNeighbour;
				}
				// check if the neighbour cell is a destination
				else if (theNeighbour.isDestination() && currentCell.isStart()
						|| theNeighbour.isDestination() && currentCell.isIntersection()
						|| theNeighbour.isDestination() && currentCell.isNorthRoad() && i == 0
						|| theNeighbour.isDestination() && currentCell.isEastRoad() && i == 1
						|| theNeighbour.isDestination() && currentCell.isSouthRoad() && i == 2
						|| theNeighbour.isDestination() && currentCell.isWestRoad() && i == 3) {

					return theNeighbour;
				}
				// check if the neighbour cell is a North road
				else if (theNeighbour.isNorthRoad() && i == 0 && currentCell.isIntersection()
						|| theNeighbour.isNorthRoad() && i == 0 && currentCell.isNorthRoad()) {

					return theNeighbour;
				}

				// check if the neighbour cell is a East road
				else if (theNeighbour.isEastRoad() && i == 1 && currentCell.isEastRoad()
						|| theNeighbour.isEastRoad() && i == 1 && currentCell.isIntersection()) {

					return theNeighbour;
				}

				// check if the neighbour cell is a South road
				else if (theNeighbour.isSouthRoad() && i == 2 && currentCell.isSouthRoad()
						|| theNeighbour.isSouthRoad() && i == 2 && currentCell.isIntersection()) {

					return theNeighbour;
				}

				// check if the neighbour cell is a West road
				else if (theNeighbour.isWestRoad() && i == 3 && currentCell.isWestRoad()
						|| theNeighbour.isWestRoad() && i == 3 && currentCell.isIntersection()) {

					return theNeighbour;
				}
			}
		}
		return null;
	}

}
