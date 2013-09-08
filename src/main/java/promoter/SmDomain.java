package promoter;

import java.util.Collection;

import com.google.common.collect.Sets;

public class SmDomain extends SmObject {

	private String name;
	private String description;
	private final Collection<String> realmIds = Sets.newHashSet();
	private final Collection<String> policyIds = Sets.newHashSet();
	private final Collection<String> ruleIds = Sets.newHashSet();
	private final Collection<String> ruleGroupIds = Sets.newHashSet();


	public SmDomain(SmRepository repo, String s) {
		super(repo, s);
	}
	
	public SmDomain withName(String s) {
		this.name = s;
		return this;
	}

	public SmDomain withDescription(String s) {
		this.description = s;
		return this;
	}

	public SmDomain withRealms(Collection<String> sl) {
		this.realmIds.clear();
		if (sl != null) {
			this.realmIds.addAll(sl);
		}
		return this;
	}

	public SmDomain withRuleGroups(Collection<String> sl) {
		this.ruleGroupIds.clear();
		if (sl != null) {
			this.ruleGroupIds.addAll(sl);
		}
		return this;
	}

	@Override
	public String toString() {
		return "SmDomain [name=" + name + ", description=" + description + ", realmIds=" + realmIds + ", policyIds="
				+ policyIds + ", ruleIds=" + ruleIds + ", ruleGroupIds=" + ruleGroupIds + "]";
	}

	public SmDomain withRules(Collection<String> sl) {
		this.ruleIds.clear();
		if (sl != null) {
			this.ruleIds.addAll(sl);
		}
		return this;
	}

	public SmDomain withPolicies(Collection<String> sl) {
		this.policyIds.clear();
		if (sl != null) {
			this.policyIds.addAll(sl);
		}
		return this;
	}
}
