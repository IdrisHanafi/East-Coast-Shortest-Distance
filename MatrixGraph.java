import java.util.*;

public class MatrixGraph {
    public vertexMatrix[][] matrix;
    public int numNodes;
    public static String[] states;
    public double INFINITY = Double.POSITIVE_INFINITY;

    //Create a blank graph with space for size nodes
    public MatrixGraph(int size, String[] inStates) {
        states = new String[size];
        states = inStates;
        this.numNodes = size;
        this.matrix = newNodeMatrix(numNodes);
    }

    public vertexMatrix[][] newNodeMatrix(int n) {
        vertexMatrix[][] nnm = new vertexMatrix[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                nnm[i][j] = new vertexMatrix(INFINITY, states[i], states[j]);
                nnm[j][i] = new vertexMatrix(INFINITY, states[j], states[i]);
            }
        }
        return nnm;
    }

    //Sets the weight of the edge (n1, n2).
    public boolean setEdge(int n1, int n2, double edge) {
        if (edge == matrix[n1][n2].distance)
            return false;

        matrix[n1][n2] = new vertexMatrix(edge, states[n1], states[n2]);
        matrix[n2][n1] = new vertexMatrix(edge, states[n2], states[n1]);
        return true;
    }

    public double getEdge(int n1, int n2) {
        return matrix[n1][n2].getDistance();
    }

    //Calculates the length of the shortest path
    public vertexMatrix[][] shortestPath() {
        int temp = 0;
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = i; j < numNodes; j++) {
                    double min = Math.min(matrix[i][j].getDistance(), matrix[i][k].getDistance() + matrix[k][j].getDistance());
                    if((matrix[i][k].getDistance() + matrix[k][j].getDistance()) > matrix[i][j].getDistance()) {
                        matrix[i][k].setPath(matrix[k][i].getPath() + " " + matrix[i][j].getFrom() + " " + matrix[i][j].getTo());
                        matrix[k][i].setPath(matrix[i][k].getPath() + " " + matrix[j][i].getFrom() + " " + matrix[j][i].getTo() + "\n");
                    } else {
                        matrix[i][k].setPath(matrix[k][i].getPath() + " " + matrix[k][i].getFrom() + " " + matrix[j][k].getTo());
                        matrix[k][i].setPath(matrix[i][k].getPath() + " " + matrix[i][k].getFrom() + " " + matrix[k][j].getTo() + "\n");
                    }
                    matrix[i][j].setDistance(min);
                    matrix[j][i].setDistance(min);
                }
            }
        }
        return matrix;
    }
}

