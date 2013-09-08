package promoter;

public class SmObject {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmObject other = (SmObject) obj;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		return true;
	}

	private String oid;
	private SmRepository repo;

	public SmObject(SmRepository repo, String oid) {
		this.repo = repo;
		this.oid = oid;
	}

	public String oid() { return oid; }
}
