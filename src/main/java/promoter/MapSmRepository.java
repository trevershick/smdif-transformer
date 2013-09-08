package promoter;

import static com.google.common.collect.Iterables.tryFind;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class MapSmRepository implements SmRepository {
	
	private final List<SmObject> smObjects = Lists.newArrayList();
	
	public SmObject byOid(final String oid) {
		return tryFind(smObjects, new Predicate<SmObject>(){
			public boolean apply(SmObject input) {
				return StringUtils.equals(oid, input.oid());
			}}).orNull();
	}

	public void add(SmObject d) {
		smObjects.add(d);
	}

	@Override
	public String toString() {
		return "MapSmRepository [smObjects=" + smObjects + "]";
	}

	
}
