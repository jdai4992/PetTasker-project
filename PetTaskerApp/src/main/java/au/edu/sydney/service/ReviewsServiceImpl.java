package au.edu.sydney.service;

import au.edu.sydney.dao.ReviewsDao;
import au.edu.sydney.model.Reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsDao reviewsDao;

    @Override
    public void addNewReviews(Reviews review) {
        reviewsDao.addNewReviews(review);
    }

    @Override
    public Reviews getReviews(int ReviewsId) {
        return reviewsDao.getReviews(ReviewsId);
    }

    @Override
    public List getAllReviews() {
        return reviewsDao.getAllReviews();
    }
}
