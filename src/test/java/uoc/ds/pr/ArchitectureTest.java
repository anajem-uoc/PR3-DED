package uoc.ds.pr;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
public class ArchitectureTest {
    private final JavaClasses importedClasses = new ClassFileImporter().importPackages("uoc.ds.pr");

    @Test
    public void shouldNotUseProhibitedJavaUtilClasses() {
        ArchRule rule = noClasses()
                .should().dependOnClassesThat()
                .resideInAnyPackage("java.util")
                .andShould().dependOnClassesThat()
                .haveNameMatching("java\\.util\\.(Vector|TreeSet|Stack|PriorityQueue|LinkedList|LinkedHashSet|Hashtable|HashSet|HashMap|Dictionary|Collections|ArrayList|ArrayDeque|AbstractSet|AbstractSequentialList|AbstractQueue|AbstractMap|AbstractList|AbstractCollection|Iterator)")
                ;

        rule.check(importedClasses);
    }

}
