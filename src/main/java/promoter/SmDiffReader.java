package promoter;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SmDiffReader {
	private static final Pattern LINE_PATTERN = Pattern.compile("(.+)\\: (.*)");
	
	private Reader reader;
	private boolean inEntry = false;
	
	
	public SmDiffReader(Reader reader) {
		this.reader = reader;
	}
	
	public SmRepository parse() throws IOException {
		final SmRepository repository = new MapSmRepository();
		final Map<String, String> record = Maps.newHashMap();
		
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while ((line = br.readLine()) != null) {
			if (isComment(line)) continue;
			if (isBlank(line)) {
				if (inEntry) {
					endOfEntry(repository, record);
					inEntry = false;
				}
				continue;
			}
			inEntry = true;
			splitLine(line, record);
		}
		return repository;
	}

	private void endOfEntry(SmRepository repository, Map<String, String> record) {
		String objectClass = record.get("objectclass");
		Function<Map<String, String>, SmObject> f = handlers(repository).get(objectClass);
		if (f == null) return;

		SmObject obj = f.apply(record);
		repository.add(obj);
	}
	
	private Map<String,Function<Map<String, String>,SmObject>> handlers(final SmRepository repo) {
		Map<String,Function<Map<String, String>,SmObject>> hs = Maps.newHashMap();
		hs.put("Domain", new Function<Map<String,String>, SmObject>() {
			public SmObject apply(Map<String, String> e) {
				SmDomain d = new SmDomain(repo, s(e,"Oid"))
					.withName(s(e,"Name"))
					.withDescription(s(e,"Desc"))
					.withRealms(sl(e,"Realms"))
					.withPolicies(sl(e,"Policies"))
					.withRules(sl(e,"Rules"))
					.withRuleGroups(sl(e,"RuleGroups"));
				return d;
			}
		});
		return hs;
	}
	
	
	private Collection<String> sl(Map<String, String> e, String string) {
		if (e.containsKey(string)) {
			return Lists.newArrayList(Splitter.on(',').trimResults().split(e.get(string)));
		}
		return Collections.emptyList();
	}
	private String s(Map<String, String> e, String string) {
		return (String) e.get(string);
	}
	
	

	private boolean isComment(String line) {
		return line.startsWith("#");
	}

	private void splitLine(String line, Map<String, String> record) {
		Matcher m = LINE_PATTERN.matcher(line);
		if (m.matches()) {
			record.put(m.group(1), m.group(2));
		}
	}
}
