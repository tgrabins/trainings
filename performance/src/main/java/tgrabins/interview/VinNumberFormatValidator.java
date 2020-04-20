package tgrabins.interview;

import java.util.stream.Collectors;

public class VinNumberFormatValidator {

    public boolean checkVinFormat(String vin){
    if (vin == null){
        throw new IllegalArgumentException("Null VIN");
    }
    boolean result = false;
    vin = vin.trim();
    if (vin.length()==10 || isNewVin(vin)){
        vin =  vin.chars()
           .filter(Character::isDigit)
           .mapToObj(c->String.valueOf((char)c))
           .collect(Collectors.joining());

        if (vin.length()==10 && validateCheckDigit(vin)){
            result = true;
        }
    }


    return result;
    }

    private boolean isNewVin(String vin) {
        return vin.length()==11 && vin.substring(9,10).equals("-");
    }


    protected boolean validateCheckDigit(String vin){
        System.out.println(vin);
        int givenCheckDigit = Integer.valueOf(vin.substring(9));
        int calulatedCheckDigit = 0;
        for(int i=0; i < 9;i++){
            System.out.println("vin substring for i=" + i + " is " + Integer.valueOf(vin.substring(i,i+1)));
            calulatedCheckDigit+=i*Integer.valueOf(vin.substring(i,i+1));
        }
        calulatedCheckDigit = calulatedCheckDigit%11;

        System.out.println(givenCheckDigit);
        System.out.println(calulatedCheckDigit);
        System.out.println("-------");

        return calulatedCheckDigit==givenCheckDigit;
    }
}
