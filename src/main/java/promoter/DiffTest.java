package promoter;

import com.railinc.sso.model.Module;
import com.railinc.sso.model.ModuleRole;
import com.railinc.sso.modules.ModuleService;

public class DiffTest {

	public static void main(String[] args) {
		
		
		Module base = new Module("SSO");
		base.addRole(new ModuleRole("SSO", "SSOADM"));
		
		
		Module working = new Module("SSO");
		working.addRole(new ModuleRole("SSO", "SSOADM").withRequestable(true));
		working.addRole(new ModuleRole("SSO", "SSOSUPPORT"));
		
		// export working as json
		
		// given base, import json
		// have json 'update' the existing system to match 'working'

		
		
	}
}
