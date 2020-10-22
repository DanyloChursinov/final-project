package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.entity.review.Review;
import com.chursinov.beautysalon.service.AppointmentService;
import com.chursinov.beautysalon.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomePageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        AppointmentService service = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        ReviewService reviewService = (ReviewService) request.getServletContext().getAttribute("ReviewService");
        List<Review> reviews = reviewService.getAllReviews();
        request.setAttribute("reviews", reviews);
        List<String> workingHours = service.getWorkingHours();
        request.setAttribute("startWork", workingHours.get(0));
        request.setAttribute("endWork", workingHours.get(1));
        return new ActionResult(Constants.Pages.INDEX_PAGE);
    }
}
