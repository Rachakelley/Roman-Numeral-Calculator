/*
 * This class contains the helper methods used in the roman calculator.
 * The roman numeral calculator allows you to add, subtract, divide,
 * mod, and multiply roman numerals. The integer representation of each
 * operand as well as the result is shown in addition to the roman numeral
 * representations.
*/

public class RomanCalculator {
    private String roman;
    private int romanAsInt;

    public RomanCalculator() {
        roman = "";
        romanAsInt = 0;
    }

    public void setRoman(String rn) {
        roman = rn;
    }

    public String getRoman() {
        return roman;
    }

    public int getRomanAsInteger() {
        return romanAsInt;
    }
    public int convert_Roman_To_Int(String rn) { // takes a string but searches as a char  
        romanAsInt = 0;
        int x = 0;
        do {
            switch (rn.charAt(x)) { // need to search as a char, will not read correctly as a String
                case 'M':
                    romanAsInt += 1000;
                    break;

                case 'D':
                    romanAsInt += 500;
                    break;

                case 'C':
                    romanAsInt += 100;
                    break;

                case 'L':
                    romanAsInt += 50;
                    break;

                case 'X':
                    romanAsInt += 10;
                    break;

                case 'V':
                    romanAsInt += 5;
                    break;

                case 'I':
                    romanAsInt += 1;
                    break;
                default: throw new NumberFormatException
                ("Illegal character \"" + rn + "\" in Roman numeral");
            }
            x++;
        }
        while (x < rn.length());
        return romanAsInt;
    }

    public String convert_Int_To_Roman(int n) {
        roman = "";
        while(n > 0) { // n is result
            int temp;
            if((n / 1000) > 0) {
                temp = n / 1000;
                n = n % 1000;
                for(int i = 0; i < temp; i++)
                    roman += "M";	
            }
            if((n / 500) > 0) {
                temp = n / 500;
                n = n % 500;
                for(int i = 0; i < temp; i++)
                    roman += "D";
            }
            if((n / 100) > 0) {
                temp = n / 100;
                n = n % 100;
                for(int i = 0; i < temp; i++)
                    roman += "C";
            }
            if((n / 50) > 0) {
                temp = n / 50;
                n = n % 50;
                for(int i = 0; i < temp; i++)
                    roman += "L";
            }
            if((n / 10) > 0) {
                temp = n / 10;
                n = n % 10;
                for(int i = 0; i < temp; i++)
                    roman += "X";
            }
            if((n / 5) > 0) {
                temp = n / 5;
                n = n % 5;
                for(int i = 0; i < temp; i++)
                    roman += "V";
            }
            if((n / 1) > 0) {
                temp = n / 1;
                n = n % 1;
                for(int i = 0; i < temp; i++)
                    roman += "I";
            }
        }
        return roman;
    }

    public void display_Roman() {
        System.out.println(roman);
    }
}
