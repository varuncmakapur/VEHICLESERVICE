package model;

public class Service {
    private int id;
    private String name;

    public Service(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + "," + name;
    }

    public static Service fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            return new Service(Integer.parseInt(parts[0]), parts[1]);
        }
        return null;
    }
}
