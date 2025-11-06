package model;

public class Booking {
    private String userEmail;
    private String vehicleNo;
    private String serviceName;
    private String date;
    private String status;

    public Booking(String userEmail, String vehicleNo, String serviceName, String date, String status) {
        this.userEmail = userEmail;
        this.vehicleNo = vehicleNo;
        this.serviceName = serviceName;
        this.date = date;
        this.status = status;
    }

    public String getUserEmail() { return userEmail; }
    public String getVehicleNo() { return vehicleNo; }
    public String getServiceName() { return serviceName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return userEmail + "," + vehicleNo + "," + serviceName + "," + date + "," + status;
    }

    public static Booking fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            return new Booking(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }
}
