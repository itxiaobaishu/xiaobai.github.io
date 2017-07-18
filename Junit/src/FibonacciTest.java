import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.rmi.CORBA.Util;
import javax.swing.text.AbstractDocument.BranchElement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FibonacciTest {
    
    public static Collection<Object[]> data() {
        Collection<Object[]> util = FibonacciTest.util();
        return util;
//        return Arrays.asList(util);
    }

    private int fInput;

    private int fExpected;

    public FibonacciTest(int input, int expected) {
        fInput= input;
        fExpected= expected;
    }
    @Parameters(name = "{index}:fib({0})={1}")
    public static Collection<Object[]> util(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\test.txt"));
            String rl = null; 
            List<Object[]> arrayList = new ArrayList<Object[]>();
            while ((rl = br.readLine()) != null) {
                String[] s = rl.split("[,]");
                Object[] arr = {
                        Integer.valueOf(s[0].trim()),
                        Integer.valueOf(s[1].trim()),
                };
                arrayList.add(arr);                
            }
            return arrayList;
             
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (br != null) {
                    
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void test() {
        assertEquals(fExpected, Fibonacci.compute(fInput));
    }
}
