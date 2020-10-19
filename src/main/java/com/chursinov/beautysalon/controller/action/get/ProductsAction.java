package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.post.AddAppointmentAction;
import com.chursinov.beautysalon.entity.Product;
import com.chursinov.beautysalon.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        ProductService service = (ProductService) request.getServletContext().getAttribute("ProductService");
        List<Product> products = service.getAllProducts();
        request.setAttribute("services", products);

        AddAppointmentAction.correctValuesForPicker(request);

        return new ActionResult(Constants.Pages.SERVICE_PAGE);
    }
}
