class vertexMatrix {
    double distance;
    String from;
    String to;
    String path = "";

    public vertexMatrix(double inDouble) {
        distance = inDouble;
    }

    public vertexMatrix(double inDouble, String start, String end) {
        distance = inDouble;
        from = start;
        to = end;
    }

    public void setDistance(double inDouble) {
        distance = inDouble;
    }

    public void setPath(String inPath) {
        path = inPath;
    }

    public void setValues(double inDouble, String inPath) {
        distance = inDouble;
        path = inPath;
    }

    public double getDistance() {
        return distance;
    }
    public String getPath() {
        return path;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
}
