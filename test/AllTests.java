
import operators.ProjectTest;
import operators.RestrictTest;
import operators.ProductTest;
import operators.CombineOperatorsTest;

import org.junit.runner.*;
import org.junit.runners.*;
import structure.TableTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TableTest.class,	
        ProjectTest.class,
        RestrictTest.class,
        ProductTest.class,
        CombineOperatorsTest.class
})
public class AllTests { }
