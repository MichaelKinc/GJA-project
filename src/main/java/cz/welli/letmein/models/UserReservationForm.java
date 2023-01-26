package cz.welli.letmein.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserReservationForm {
    private Activity activity;
    private Place place;
    private LocalDate date;

    /** Attributes for creating Reservation object */
    private LocalTime start;
    private int requiredCapacity;
    private boolean confirmation;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        System.out.println(activity);
        this.activity = activity;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date.isEmpty()) {
            return;
        }
        this.date = LocalDate.parse(date);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = LocalTime.parse(start);
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }

    public void setRequiredCapacity(int requiredCapacity) {
        this.requiredCapacity = requiredCapacity;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "UserReservationsForm [activity=" + activity.getType() + "place="+ place.getCapacity() +"]";
    }

    /*public int getProgressStep() {
        int step = 1;
        if (activity != null) {
            step = 2;
            if (place != null) {
                step = 3;
                if (date != null) {
                    step = 4;
                }
            }
        }
        return step;
    }

    public boolean isStep(int step) {
        return getProgressStep() == step;
    }

    public boolean currentStepAheadOfStep(int step) {
        return getProgressStep() > step;
    }*/
}
