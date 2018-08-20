package au.edu.sydney.dao;

import au.edu.sydney.model.Reviews;

import java.util.List;

public interface ReviewsDao {

    void addNewReviews (Reviews review);
    Reviews getReviews (int ReviewsId);
    List getAllReviews();

}