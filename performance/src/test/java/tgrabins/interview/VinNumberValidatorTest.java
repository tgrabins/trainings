package tgrabins.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VinNumberValidatorTest {

    VinNumberFormatValidator validator;

    @BeforeEach
    void setUp() {
        validator = new VinNumberFormatValidator();
    }

    @Test
    void whenNullGivenThrowIllegalArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            validator.checkVinFormat(null);
        });
    }

    @Test
    void whenWrongFormatReturnFalse() {
        Assertions.assertFalse(validator.checkVinFormat(""));
        Assertions.assertFalse(validator.checkVinFormat(",,"));
        Assertions.assertFalse(validator.checkVinFormat("AAABBBCCC21"));
        Assertions.assertFalse(validator.checkVinFormat("04719586924"));
        Assertions.assertFalse(validator.checkVinFormat("047195869A"));
    }

    @Test
    void whenWrongCheckDigitReturnFalse() {
        //this vin should has check digit 2
        Assertions.assertFalse(validator.checkVinFormat("0471958691"));
        Assertions.assertFalse(validator.checkVinFormat("0471958693"));
        Assertions.assertFalse(validator.checkVinFormat("0471958694"));
        Assertions.assertFalse(validator.checkVinFormat("0471958695"));
        Assertions.assertFalse(validator.checkVinFormat("0471958696"));
        Assertions.assertFalse(validator.checkVinFormat("0471958697"));
        Assertions.assertFalse(validator.checkVinFormat("0471958698"));
        Assertions.assertFalse(validator.checkVinFormat("0471958699"));
        Assertions.assertFalse(validator.checkVinFormat("0471958690"));

    }

    @Test
    void whenCorrectFormatReturnTrue() {
        Assertions.assertTrue(validator.checkVinFormat("0471958692"));
        Assertions.assertTrue(validator.checkVinFormat("0471606958"));
    }

}