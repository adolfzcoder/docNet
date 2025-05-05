package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

class Rating {
    private static ArrayList<Rating> allRatings = new ArrayList<>();
   private int ratingID;
   private int doctorID;
   private String review;
   private double score;
   private LocalDateTime dateTime;

   //constructor
    /*
    old rating constructor
   public Rating (int ratingID, int doctorID, String review,double score, LocalDateTime dateTime ){
       setRatingID(ratingID);
       setDoctorID(doctorID);
       setReview(review);
       setScore(score);
       setDateTime(dateTime);
   }
   */


   //add rating method
   public static void addRating(Rating rating){
       if (rating.validateRating()) {
           allRatings.add(rating);
           rating.confirmRating();
       }
   }
   // deleteRating
   public static void deleteRating(int ratingID) {
       allRatings.removeIf(r -> r.getRatingID() == ratingID);
   }

   //updateRating
   public static void updateRating(int ratingID, String newReview, double newScore) {
       for (Rating r : allRatings) {
           if (r.getRatingID() == ratingID) {
               r.setReview(newReview);
               r.setScore(newScore);
               r.setDateTime(LocalDateTime.now());
           }
       }
   }

   //aerageScore method
   public static double averageDoctorRating(int doctorID) {
       double total = 0;
       int count = 0;
       for (Rating r : allRatings) {
           if (r.getDoctorID() == doctorID) {
               total += r.getScore();
               count++;
           }
       }
       return count > 0 ? total / count : 0;
   }

   //comfirmRatingMethod
   public void confirmRating() {
       System.out.println("Rating ID " + ratingID + " confirmed for Doctor ID " + doctorID);
   }

   //validating method
   public boolean validateRating() {
       return score >= 0 && score <= 5 && review != null && !review.isBlank();
   }







    public int getRatingID() {
        return ratingID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getReview() {
        return review;
    }

    public double getScore() {
        return score;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }



//setters
    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

