package cz.welli.letmein.models;

public class PinCodeFormResponse {
    private String pin;

    public PinCodeFormResponse(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return this.pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean comparePin(String pin) {
        return this.pin.equals(pin);
    }

    public boolean isValid() {
        return (this.pin.length() == 6);
    }
}
