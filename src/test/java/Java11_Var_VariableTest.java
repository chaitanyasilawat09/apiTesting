import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Java11_Var_VariableTest {
    Java11_Var_Variable java = new Java11_Var_Variable();


    @Test
    public void testTest12() {


    assertEquals(java.test12(2,4),8,"value mot equals");
    }
}