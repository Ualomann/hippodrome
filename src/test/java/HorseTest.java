import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void TestConstructorThrowsExceptionWhenFirstParameterIsNull() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.0));
        assertEquals("Name cannot be null.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ", "   ", "\t", "\n", "\t\n "})
    public void TestConstructorThrowsExceptionWhenSecondParameterIsEmpty(String parameter) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, 2.0));
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @Test
    public void TestConstructorThrowsExceptionWhenSecondParameterIsNegativeNumber() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("Bulka", -1));
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    public void TestConstructorThrowsExceptionWhenThirdParameterIsNegativeNumber() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> new Horse("Bulka", 2.0,-1));
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

        @Test
        public void TestGetNameReturnsCorrectName() {
            String testName = "Bulka";
            Horse horse = new Horse(testName, 2.0);
            assertEquals(testName, horse.getName());
        }

        @Test
        public void TestGetSpeedReturnsCorrectSpeed() {
        Double testSpeed = 2.0;
        Horse horse = new Horse("Bulka", testSpeed);
        assertEquals(testSpeed, horse.getSpeed());
        }

        @Test
        public void TestGetDistanceReturnsCorrectDistance() {
        double testDistance = 10.0;
        Horse horse = new Horse("Bulka", 2.0,testDistance);
        assertEquals(testDistance, horse.getDistance());
        }
            @Test
            public void TestGetDistanceReturnsZeroTwoParameterConstructor() {
                Horse horse = new Horse("Bulka", 2.0);
                assertEquals(0, horse.getDistance());
            }

    @Test
    public void TestMoveCallsGetRandomDouble() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bulka", 2.0);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3,0.7,12.0})
    public void TestMoveUpdatesDistance(double notRandom){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(notRandom);
            Horse horse = new Horse("Bulka", 2.0,10.0);
            horse.move();
            double testDistance = 10.0 + 2.0 * notRandom;
            assertEquals(testDistance, horse.getDistance(),"Distance should be " + testDistance);
        }
    }
}
