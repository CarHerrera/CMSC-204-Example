import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeBookTester {

    GradeBook grade1;
    GradeBook grade2;

    @BeforeEach
    void setUp(){
        grade1 = new GradeBook(5);
        grade2 = new GradeBook(5);
        grade1.addScore(1);
        grade1.addScore(100);
        grade1.addScore(50);
        grade2.addScore(40);
        grade2.addScore(60);
        grade2.addScore(80);
        grade2.addScore(20);
    }

    @AfterEach
    public void tearDown(){
        grade1 = grade2 = null;
    }

    @Test
    void testAddScore(){
        grade1.addScore(20);
        assertEquals("1.0 100.0 50.0 20.0 ", grade1.toString());
        assertEquals("40.0 60.0 80.0 20.0 ", grade2.toString());
    }

    @Test
    void testSum() {
        assertEquals(151, grade1.sum());
        assertEquals(200, grade2.sum());
    }

    @Test
    void testMinimum() {
        assertEquals(1.0, grade1.minimum());
        assertEquals(20.0, grade2.minimum());
    }

    @Test
    void testFinalScore(){
        assertEquals(150, grade1.finalScore());
        assertEquals(180, grade2.finalScore());
    }

    @Test
    void testGetScoreSize(){
        assertEquals(3,grade1.getScoresSize());
        assertEquals(4,grade2.getScoresSize());
    }
}
