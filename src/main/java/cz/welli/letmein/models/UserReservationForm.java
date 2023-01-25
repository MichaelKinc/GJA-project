package cz.welli.letmein.models;

import java.time.LocalDate;

public class UserReservationForm {
    private Activity activity;
    private Place place;

    private LocalDate date;

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
