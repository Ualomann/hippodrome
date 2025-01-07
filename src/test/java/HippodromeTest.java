
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    public void TestConstructorThrowsExceptionWhenParameterIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }
    @Test
    public void TestConstructorThrowsExceptionWhenEmptyList() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    public void TestGetHorsesCorrectlyList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Bulka " + i, 1.0 + i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void TestMove() {
        try(MockedStatic<Hippodrome> mockedStatic = mockStatic(Hippodrome.class)) {
            ArrayList<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                horses.add(mock(Horse.class));
            }
            Hippodrome hippodrome = new Hippodrome(horses);

            hippodrome.move();

            for (Horse horse : horses) {
                verify(horse,times(1)).move();
            }
        }
    }

    @Test
    public void testGetWinner(){
        List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("Bulka ", 2.0,10.0);
        Horse horse2 = new Horse("Bublik ", 2.0,4);
        Horse horse3 = new Horse("Rogalik ", 2.0,2.5);
        Horse horse4 = new Horse("Ponchik ", 2.0,3);
        Horse horse5 = new Horse("Cheese ", 2.0,1);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);
        horses.add(horse5);

        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horse1, hippodrome.getWinner());
    }

}
