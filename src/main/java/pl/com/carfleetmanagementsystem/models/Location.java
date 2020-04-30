package pl.com.carfleetmanagementsystem.models;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @OneToOne(mappedBy = "gpsLocation")
    private CarLogs carLogs;

    private boolean hasCoordinates;
    private float latitude;
    private float longitude;
    private String address;

    public Location() {
    }

    public Location(Long locationId) {
        this.locationId = locationId;
    }

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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof pl.com.carfleetmanagementsystem.models.Location)) {
            return false;
        } else {
            pl.com.carfleetmanagementsystem.models.Location location = (pl.com.carfleetmanagementsystem.models.Location)o;
            if (this.hasCoordinates != location.hasCoordinates) {
                return false;
            } else {
                if (this.hasCoordinates) {
                    if (Float.compare(location.latitude, this.latitude) != 0) {
                        return false;
                    }

                    if (Float.compare(location.longitude, this.longitude) != 0) {
                        return false;
                    }
                }

                return this.address.equals(location.address);
            }
        }
    }

    public int hashCode() {
        int result = this.address.hashCode();
        if (this.hasCoordinates) {
            result = 29 * result + Float.floatToIntBits(this.latitude);
            result = 29 * result + Float.floatToIntBits(this.longitude);
        }

        return result;
    }
}