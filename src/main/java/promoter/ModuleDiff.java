package promoter;

import java.util.List;

import com.railinc.sso.model.Module;
import com.railinc.sso.model.ModuleRole;

import de.danielbechler.diff.ObjectDifferFactory;

public class ModuleDiff {

	private Module base;
	private Module working;

	public ModuleDiff(Module working, Module base) {
		this.working = working;
		this.base = base;
	}

	public void diff() {
		if (base == null) {
			// module is added?
			this.differences.add(moduleAdded(working));
			List<ModuleRole> roles = working.getRoles();
			for (ModuleRole mr : roles) {
				this.differences.add(roleAdded(mr));
			}
			return;
		}
		
		// module is updated?
		this.differences.addAll(moduleUpdated(working,base));
		
		// role is added?
		// role is updated?
		// role is deleted?
	}
	
	private Collection<Differences> moduleUpdated(Module working2, Module base2) {
		
	}

	public ModulePatch generatePatch() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
