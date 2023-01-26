package cz.welli.letmein.dto;

import java.time.LocalTime;

public class ReservationSlotDTO {
    private LocalTime from;
    private LocalTime to;
    private int availableCapacity;
    private int requiredCapacity;
    private int maxCapacity;

    public ReservationSlotDTO(LocalTime from, LocalTime to, int availableCapacity, int maxCapacity) {
        this.from = from;
        this.to = to;
        this.availableCapacity = availableCapacity;
        this.maxCapacity = maxCapacity;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public void setRequiredCapacity(int requiredCapacity) {
        this.requiredCapacity = requiredCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "ReservationSlotDTO[from=" + from + ", to=" + to + ", cap=" + availableCapacity + "]";
    }
}
