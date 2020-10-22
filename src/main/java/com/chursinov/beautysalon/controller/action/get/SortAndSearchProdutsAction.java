package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.entity.product.Product;
import com.chursinov.beautysalon.service.ProductService;
import com.chursinov.beautysalon.util.ProductsSorter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortAndSearchProdutsAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = getProductByCriteria(request);
        request.setAttribute("services", products);
        return new ActionResult(Constants.Pages.SERVICE_PAGE);
    }

    private List<Product> getProductByCriteria(HttpServletRequest request) {
        String sortCriteria = request.getParameter("sortBy");
        String searchCriteria = request.getParameter("searchBy");
        String value = request.getParameter("searchText");

        ProductService service = (ProductService) request.getServletContext().getAttribute("ProductService");
        List<Product> products;
        switch (searchCriteria) {
            case "all":
                products = service.getAllProducts();
                ProductsSorter.sort(products, sortCriteria);
                break;
            case "masters":
                products = service.getProductsByMaster(value);
                ProductsSorter.sort(products, sortCriteria);
                break;
            case "services":
                products = service.getProductsByName(value);
                ProductsSorter.sort(products, sortCriteria);
                break;
            default:
                throw new IllegalArgumentException("invalid type of criteria");
        }
        return products;
    }
}
