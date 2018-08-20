package au.edu.sydney.service;


import java.util.List;

import au.edu.sydney.model.Reviews;

public interface ReviewsService {

    void addNewReviews (Reviews reviews);
    Reviews getReviews (int ReviewsId);
    List getAllReviews();

}