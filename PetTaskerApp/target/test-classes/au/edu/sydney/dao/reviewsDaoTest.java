package au.edu.sydney.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import au.edu.sydney.model.Reviews;
import au.edu.sydney.dao.ReviewsDao;

@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class reviewsDaoTest {
	
	@Autowired
	private ReviewsDao reviewsDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void addNewReviewsTest(){
		Reviews review = new Reviews();
		review.setAttitude(5);
		review.setHonesty(4);
		review.setEfficiency(3);
		review.setOverall(2);
		
		reviewsDao.addNewReviews(review);
		List<Reviews> reviews = reviewsDao.getAllReviews();
		
		Assert.assertEquals(review.getAttitude(), reviews.get(reviews.size()-1).getAttitude());
		Assert.assertEquals(review.getEfficiency(), reviews.get(reviews.size()-1).getEfficiency());
		Assert.assertEquals(review.getHonesty(), reviews.get(reviews.size()-1).getHonesty());
		Assert.assertEquals(review.getOverall(), reviews.get(reviews.size()-1).getOverall());
	}

}
