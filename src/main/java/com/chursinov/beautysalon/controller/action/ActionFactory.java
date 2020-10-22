package com.chursinov.beautysalon.controller.action;

import com.chursinov.beautysalon.controller.action.get.*;
import com.chursinov.beautysalon.controller.action.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActionFactory {

    private static Map<String, Action> actions;

    public static void init() {
        actions = new LinkedHashMap<>();
        actions.put("POST/signin", new SignInAction());
        actions.put("GET/signout", new SignOutAction());
        actions.put("POST/signup", new SignUpAction());
        actions.put("GET/service", new ProductsAction());
        actions.put("GET/admin-profile", new AdminProfileAction());
        actions.put("GET/master-profile", new MasterProfileAction());
        actions.put("GET/client-profile", new ClientProfileAction());
        actions.put("GET/search-products", new SortAndSearchProdutsAction());
        actions.put("POST/add-appointment", new AddAppointmentAction());
        actions.put("GET/review", new ReviewsAction());
        actions.put("POST/add-review", new AddReviewAction());
        actions.put("POST/done-appointment", new DoneAppointmentForMaster());
        actions.put("POST/paid-appointment", new PaidAppointmentForAdmin());
        actions.put("POST/delete-appointment", new DeleteAppointmentForAdmin());
        actions.put("POST/change-time", new ChangeTimeForAdmin());
        actions.put("GET/change-locale", new ChangeLocaleAction());
        actions.put("POST/change-working-time", new SetWorkingHoursForAdmin());
        actions.put("GET/home-page", new HomePageAction());

    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getServletPath());
    }
}


