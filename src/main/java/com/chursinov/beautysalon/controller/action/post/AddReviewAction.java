package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.service.ReviewService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddReviewAction implements Action {


    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("txtName");
        String message = request.getParameter("txtMsg");
        String stringEvaluation = request.getParameter("evaluation");
        String masterName = request.getParameter("masters");
        int evaluation = Integer.parseInt(stringEvaluation);

        ServletContext context = request.getServletContext();
        ReviewService service = (ReviewService) context.getAttribute("ReviewService");
        int masterId = service.getMasterNameById(masterName);

        service.AddReview(message,evaluation,masterId,name);

        return new ActionResult(Constants.GetActions.REVIEW, ResponseType.REDIRECT);
    }
}
