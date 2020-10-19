package com.chursinov.beautysalon.util;

import com.chursinov.beautysalon.entity.Product;

import java.util.List;

public class ProductsSorter {
    public static void sort(List<Product> products, String sortCriteria) {
        if (sortCriteria.contains("sortByMasters")) {
            sortProductsByMaster(products, sortCriteria);
        } else if (sortCriteria.contains("sortByRaiting")) {
            sortProductsByRaiting(products, sortCriteria);
        }
    }

    private static void sortProductsByMaster(List<Product> products, String sortCriteria) {
        if (sortCriteria.contains("A-Z")) {
            products.sort((c1, c2) -> c1.getMaster().compareTo(c2.getMaster()));
        } else if (sortCriteria.contains("Z-A")) {
            products.sort((c1, c2) -> c2.getMaster().compareTo(c1.getMaster()));
        }
    }

    private static void sortProductsByRaiting(List<Product> products, String sortCriteria) {
        if (sortCriteria.contains("Descending")) {
            products.sort((c1, c2) -> c2.getRaiting().compareTo(c1.getRaiting()));
        } else if (sortCriteria.contains("Ascending")) {
            products.sort((c1, c2) -> c1.getMaster().compareTo(c2.getMaster()));
        }
    }
}
