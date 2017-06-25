package composite;

import java.util.Iterator;
import java.util.List;



public class MachineCompsite extends MachineComponent{

	List<MachineComponent> machines;
	
	@Override
	public int getMachineCount() {
		int count=0;
		Iterator<MachineComponent> i= machines.iterator();
		while (i.hasNext()) {
			count+=i.next().getMachineCount();
		}
		return count;
	}

}
