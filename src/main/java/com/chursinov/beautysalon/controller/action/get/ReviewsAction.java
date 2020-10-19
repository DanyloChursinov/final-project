package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.entity.Review;
import com.chursinov.beautysalon.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReviewsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ReviewService service = (ReviewService) request.getServletContext().getAttribute("ReviewService");
        List<Review> reviews = service.getAllReviews();
        request.setAttribute("reviews", reviews);
        List<String> masters = service.getAllMastersName();
        request.setAttribute("masters", masters);
        return new ActionResult(Constants.Pages.REVIEW_PAGE);
    }
}
