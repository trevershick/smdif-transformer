package promoter;

public interface SmRepository {

	SmObject byOid(String oid);

	void add(SmObject d);
}
