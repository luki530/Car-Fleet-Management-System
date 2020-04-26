package pl.com.carfleetmanagementsystem.models;

public class Location {
    private boolean hasCoordinates;
    private float latitude;
    private float longitude;
    private String address;

    public Location(String address) {
        this.setAddress(address);
    }

    public Location(String address, float latitude, float longitude) {
        this.setAddress(address);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public boolean hasCoordinates() {
        return this.hasCoordinates;
    }

    public void clearCoordinates() {
        this.hasCoordinates = false;
        this.latitude = 0.0F;
        this.longitude = 0.0F;
    }

    public float getLatitude() {
        this.assertHasCoordinates();
        return this.latitude;
    }

    public float getLongitude() {
        this.assertHasCoordinates();
        return this.longitude;
    }

    private void assertHasCoordinates() {
        if (!this.hasCoordinates) {
            throw new IllegalStateException("No coordinates have been defined. (Check with hasCoordinates() first)");
        }
    }

    public void setLongitude(float longitude) {
        this.hasCoordinates = true;
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.hasCoordinates = true;
        this.latitude = latitude;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new NullPointerException("address cannot be null");
        } else {
            this.address = address;
        }
    }

//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        } else if (!(o instanceof com.google.api.gbase.client.Location)) {
//            return false;
//        } else {
//            com.google.api.gbase.client.Location location = (com.google.api.gbase.client.Location)o;
//            if (this.hasCoordinates != location.hasCoordinates) {
//                return false;
//            } else {
//                if (this.hasCoordinates) {
//                    if (Float.compare(location.latitude, this.latitude) != 0) {
//                        return false;
//                    }
//
//                    if (Float.compare(location.longitude, this.longitude) != 0) {
//                        return false;
//                    }
//                }
//
//                return this.address.equals(location.address);
//            }
//        }
//    }

    public int hashCode() {
        int result = this.address.hashCode();
        if (this.hasCoordinates) {
            result = 29 * result + Float.floatToIntBits(this.latitude);
            result = 29 * result + Float.floatToIntBits(this.longitude);
        }

        return result;
    }
}