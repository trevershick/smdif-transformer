package promoter;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.directory.Attribute;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ldap.core.LdapAttributes;
import org.springframework.ldap.ldif.parser.LdifParser;

public class ConverterTest {

	@Test
	public void test() throws IOException {
		SmDiffReader r = new SmDiffReader(new InputStreamReader(getClass().getResourceAsStream("/TESTAPP.smdif")));
		SmRepository parse = r.parse();
		System.out.println(parse);
	}
	
	
	public void onDomain(LdapAttributes record) {
		Attribute attribute = record.get("objectclass");
		Attribute attribute2 = record.get("Realms");
		Attribute attribute3 = record.get("Name");
		Attribute attribute4 = record.get("Rules");
		Attribute attribute5 = record.get("RuleGroups");
		Attribute attribute6 = record.get("Policies");
	}
}
