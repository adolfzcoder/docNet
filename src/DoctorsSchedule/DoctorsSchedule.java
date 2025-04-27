package DoctorsSchedule;

import java.util.ArrayList;
import java.util.List;

public class DoctorsSchedule {
    private int doctorsScheduleID;
    private int doctorID;
    private String dayOfWeek;

    private static List<DoctorsSchedule> scheduleList = new ArrayList<>();

    public DoctorsSchedule(int doctorsScheduleID, int doctorID, String dayOfWeek) {
        this.doctorsScheduleID = doctorsScheduleID;
        this.doctorID = doctorID;
        this.dayOfWeek = dayOfWeek;
    }

    // Add a new schedule
    public static void addSchedule(DoctorsSchedule schedule) {
        scheduleList.add(schedule);
    }

    // Update an existing schedule
    public static boolean updateSchedule(int doctorsScheduleID, String newDayOfWeek) {
        for (DoctorsSchedule schedule : scheduleList) {
            if (schedule.doctorsScheduleID == doctorsScheduleID) {
                schedule.dayOfWeek = newDayOfWeek;
                return true; // Successfully updated
            }
        }
        return false; // Schedule not found
    }

    // View all schedules
    public static void viewAllSchedules() {
        if (scheduleList.isEmpty()) {
            System.out.println("No schedules available.");
        } else {
            for (DoctorsSchedule schedule : scheduleList) {
                System.out.println(schedule);
            }
        }
    }

    public static boolean isScheduleEmpty() {
        return scheduleList.isEmpty();
    }

    public static boolean removeTask(int doctorsScheduleID) {
        return scheduleList.removeIf(schedule -> schedule.doctorsScheduleID == doctorsScheduleID);
    }

    @Override
    public String toString() {
        return "DoctorsSchedule{" +
                "doctorsScheduleID=" + doctorsScheduleID +
                ", doctorID=" + doctorID +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
