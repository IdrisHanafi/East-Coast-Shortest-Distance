import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CS253FinalProject {
    public static File myFile;
    public static File myFile1;
    public static int size = 0;
    public static String[][] weights;
    public static String[] states;
    public static vertexMatrix[][] vertexDistance;
    public static int row;
    public static int column;

    private static boolean validInputs(String inStart, String inEnd) {
        int trueValue = 0;
        boolean boolValue = false;
        if(inStart.equalsIgnoreCase(inEnd)) {
            System.out.println("The shortest path from " + inStart + " to " + inEnd + " is 0 miles.");
            boolValue = false;
        } else {
            for(int i = 0; i < states.length; i++) {
                if(inStart.equalsIgnoreCase(states[i])) {
                    row = i;
                    trueValue++;
                } else if(inEnd.equalsIgnoreCase(states[i])) {
                    column = i;
                    trueValue++;
                }
            }
        }
        if(trueValue == 2) {
            boolValue = true;
        } else {
            System.out.println("Invalid state(s).");
        }
        return boolValue;
    }

    private static void printStates() {
        for(int i = 0; i < states.length; i++) {
            if(states.length == i+1) {
                System.out.print(states[i]);
            } else {
                System.out.print(states[i] + ", ");
            }
        }
        System.out.println();
    }

    public static void size(Scanner inRefFile) throws FileNotFoundException {
        String line;
        while (inRefFile.hasNext()) {
            line = inRefFile.nextLine();
            size++;
        }
        size = size - 1;
        inRefFile.close();
    }
    
    public static void collectStates(String inStates) {
        states = inStates.trim().split(" ");
    }

    public static void collectWeight(int inRow, String inWeight) {
        String[] tempWeight = new String[size+1];
        tempWeight = inWeight.trim().split(" ");

        for(int i = 1; i < tempWeight.length; i++) {
            weights[inRow][i-1] = tempWeight[i];
        }
    }

    public static void readDataInputOnce() throws FileNotFoundException {
        myFile = new File("eastOfUSA.txt");
        myFile1 = new File("eastOfUSA.txt");
        Scanner refFile = new Scanner(myFile);
        size(refFile);
        Scanner inputFile = new Scanner(myFile1);
        weights = new String[size][size];
        states = new String[size];
        String line;
        int i = 0;
        int rowCount = 0;
        while (inputFile.hasNext()) {
            line = inputFile.nextLine();
            if(i == 0) {
                collectStates(line);
            } else {
                collectWeight(rowCount, line);
                rowCount++;
            }
            i++;
        }
        inputFile.close();
    }

    public static void convertStringToDouble() {
        vertexDistance = new vertexMatrix[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(weights[i][j].equals("0")) {
                    vertexDistance[i][j] = new vertexMatrix(0.00);
                } else {
                    vertexDistance[i][j] = new vertexMatrix(Double.parseDouble(weights[i][j]));
                }
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        readDataInputOnce();
        convertStringToDouble();
        MatrixGraph x = new MatrixGraph(size, states);
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(vertexDistance[i][j].getDistance() != 0.0) {
                    x.setEdge(i, j, vertexDistance[i][j].getDistance());
                }
            }
        }
        vertexDistance = x.shortestPath();
        
        String start;
        String end;
        int temp = 0;
        Scanner scan = new Scanner(System.in);
        while(temp != 1) {
            System.out.print("Enter in the starting point from the following: ");
            printStates();
            start = scan.nextLine();
            System.out.print("Enter in the finish point from the following: ");
            printStates();
            end = scan.nextLine();
            if(validInputs(start, end)) {
                System.out.print("The shortest path from " + start + " to " + end + " is: ");
                System.out.printf("%.2f miles\n", vertexDistance[row][column].getDistance());
            }
            System.out.print("Enter in 1 to quit or enter 2 to continue: ");
            temp = scan.nextInt();
            start = scan.nextLine();
            System.out.println("\n");
        }
    }
}
