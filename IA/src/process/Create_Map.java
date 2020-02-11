package process;
import java.util.Iterator;

import data.Element;
import data.Map;

public class Create_Map {
	private Map map;
	
	public Create_Map(int dimension) {
		map= new Map(dimension);
	}
	
	public void inserElt(Element e) { //on pourrais throw une exception
		Element pos;
		for(Iterator<Element> it=map.getMap().iterator();it.hasNext();) {
			pos=it.next();
			if(pos.getCoordX()==e.getCoordX() && pos.getCoordY()==e.getCoordY())
				System.err.println("Case déja défini");
			else
				map.getMap().add(e);
		}
	}
}
