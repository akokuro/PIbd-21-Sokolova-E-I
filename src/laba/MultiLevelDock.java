package laba;

import java.util.ArrayList;

public class MultiLevelDock {
	 ArrayList<Dock<IShip>> dockStages;

	    private final int countPlaces = 20;

	    public MultiLevelDock(int countStages, int pictureWidth, int pictureHeight) {
	        dockStages = new ArrayList<Dock<IShip>>();
	        for (int i = 0; i < countStages; ++i)
	        {
	            dockStages.add(new Dock<IShip>(countPlaces, pictureWidth, pictureHeight));
	        }
	    }

	    public Dock<IShip> getAt(int index) {
	        if (index > -1 && index < dockStages.size()) {
	            return dockStages.get(index);
	        }

	        return null;
	    }
}
