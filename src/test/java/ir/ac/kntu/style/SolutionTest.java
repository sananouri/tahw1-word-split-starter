/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.ac.kntu.style;

import ir.ac.kntu.WordSplit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 5 points
 * @author mhrimaz
 */
public class SolutionTest {
    static {
        System.err.println("$$$GRADER$$$ | { type:\"MSG\" , key:\"TOTAL\" , value:10, priority:1  }  | $$$GRADER$$$");
    }   
    public boolean test(String inputString, String outputString) {

        InputStream in = null;
        PrintStream printStream = null;
        try {
            File input = new File("input.txt");
            input.createNewFile();
            File output = new File("output.txt");
            output.createNewFile();
            in = new FileInputStream(input);
            printStream = new PrintStream(output);
            try (PrintWriter w = new PrintWriter(input);) {
                w.println(inputString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(output))) {
                System.setIn(in);
                System.setOut(printStream);
                WordSplit.main(new String[]{});
                return reader.readLine().trim().equals(outputString);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
                printStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Test
    public void test1() {
        assertTrue(test("Hello my name is Java. i love programmers!",
                "Hello,my,name,is,Java.,i,love,programmers!"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:2 , reason:\"Test Passed.\" } | $$$GRADER$$$" );
    }

    @Test
    public void useTrimTest() {
        assertTrue(test("        Hello my name is Java. i love programmers!     ",
                "Hello,my,name,is,Java.,i,love,programmers!"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:2 , reason:\"Test Passed.\" } | $$$GRADER$$$" );
    }

    @Test
    public void useRegexTest() {
        assertTrue(test("hello    world    !",
                "hello,world,!"));
        System.err.println("$$$GRADER$$$ | { type:\"SCORE\" , amount:1 , reason:\"Test Passed.\" } | $$$GRADER$$$" );
    }

}
