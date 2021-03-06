import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day5 {
    public static void main(String[] args) {
        getDay5(); //908
    }

    public static void getDay5() {
        BufferedReader reader;
        int highestSeatID = 0;
        int[] rowRange = {0, 127};
        int[] columnRange = {0, 7};
        char[] rowKey = {'F','B'};
        char[] columnKey = {'L','R'};
        char[][] seats = new char[128][8];
    
        try {
            reader = new BufferedReader(new FileReader(
                    "./data/Day5.data"));
            String line = reader.readLine();
            while (line != null) {
                String row = line.substring(0, line.length()-3);
                String column = line.substring(line.length()-3);
                int rowNum = search(row,rowRange, 0, rowKey);
                int colNum = search(column, columnRange, 0, columnKey);
                seats[rowNum][colNum] = '#';

                int seatID = rowNum * 8 + colNum;
                if ( seatID > highestSeatID) {
                    highestSeatID = seatID;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printSeats(seats);
        findSeat(seats);
        System.out.println("Highest seat ID: "+highestSeatID);
    }

    public static int search(String s, int[] range, int charIndex, char[] key) {
        if (charIndex == s.length()-1) {
            if (s.charAt(charIndex) == key[0]) {
                return range[0];
            }
            return range[1];
        }

        int middle = (range[1] + range[0])/2;
        if (s.charAt(charIndex) == key[0]) {
            int[] lowerHalf = {range[0],middle}; 
            return search(s, lowerHalf,charIndex + 1, key);
        }

        int[] upperHalf = {middle+1,range[1]};
        return search(s, upperHalf,charIndex+ 1, key);

    }

    public static void findSeat(char[][] seats) {
        int row = -1;
        int col = -1;
        for (int i = 1; i<seats.length-1; i++) {
            for (int j = 0; j<seats[i].length; j++) {
                if (seats[i][j] != '#' && seats[i-1][j] == '#' && seats[i+1][j] == '#') {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        int seatID = row*8+col;
        System.out.println("My seat ID is at: "+ seatID);
    }

    public static void printSeats(char[][] seats) {
        for (int i = 0; i<seats[i].length; i++) {
            for (int j = 0; j<seats.length; j++) {
                if (seats[j][i] == '#') {
                    System.out.print("#");
                }
                else {
                    System.out.print("]");
                }
            }
            if (i==1 || i==5) {
                System.out.println("");
            }
            System.out.println("");
        }
    }
}

// ]#################################################################################################################]]]]]]]]]]]]]]
// ]#################################################################################################################]]]]]]]]]]]]]]

// ]#################################################################################################################]]]]]]]]]]]]]]
// ]############################################################################]####################################]]]]]]]]]]]]]]
// ]#################################################################################################################]]]]]]]]]]]]]]
// ]################################################################################################################]]]]]]]]]]]]]]]

// ]################################################################################################################]]]]]]]]]]]]]]]
// #################################################################################################################]]]]]]]]]]]]]]]