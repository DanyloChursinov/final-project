package com.chursinov.beautysalon.constants;

public abstract class Query {

    private Query() {}

    public static final String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT `users`.id, `users`.name, `users`.email, `roles`.name AS role FROM `users` INNER JOIN `roles` ON `users`.role_id = `roles`.id WHERE `users`.email = ? AND `users`.password = ?";
    public static final String ADD_USER = "INSERT INTO `users` VALUES (DEFAULT, ?, ?, ?, ?)";
    public static final String ADD_APPOINTMENT = "INSERT INTO `appointments` VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    public static final String GET_USER_BY_EMAIL = "SELECT `users`.id, `users`.name, `users`.email, `roles`.name AS role FROM `users` INNER JOIN `roles` ON `users`.role_id = `roles`.id WHERE `users`.email = ?";
    public static final String GET_ALL_PRODUCTS = "SELECT `products`.*, `users`.name AS master FROM `products` INNER JOIN `users` ON `products`.master_id = users.id";
    public static final String GET_RATING_FOR_MASTER = "SELECT cast(AVG(`evaluation`) as DECIMAL(10,2)) AS raiting FROM reviews WHERE master_id = ?";
    public static final String GET_PRODUCTS_BY_MASTER = "SELECT `products`.*, `users`.name AS master FROM `products` INNER JOIN `users` ON `products`.master_id = users.id WHERE `users`.name = ?";
    public static final String GET_PRODUCTS_BY_NAME = "SELECT `products`.*, `users`.name AS master FROM `products` INNER JOIN `users` ON `products`.master_id = users.id WHERE `products`.name = ?";
    public static final String GET_ALL_REVIEWS ="SELECT `reviews`.*, `users`.name AS master FROM `reviews` INNER JOIN `users` ON `reviews`.master_id = users.id";
    public static final String GET_ALL_MASTERS_NAME="SELECT users.name FROM users WHERE role_id = 2";
    public static final String GET_MASTER_ID_BY_NAME="SELECT id FROM users WHERE users.name=?";
    public static final String ADD_REVIEW = "INSERT INTO `reviews` VALUES (DEFAULT, ?, ?, ?, ?);";
    public static final String GET_APPOINTMENTS_BY_USER = "SELECT `appointments`.id, `appointments`.start_time, `appointments`.end_time, `appointments`.appointment_master_status, `appointments`.appointment_admin_status, `appointments`.client_id, (SELECT users.name FROM users WHERE users.id=client_id) AS client, products.id, `products`.name, `products`.price,`products`.duration , `products`.master_id, users.name AS master FROM appointments LEFT JOIN products ON appointments.product_id=products.id LEFT JOIN users ON products.master_id=users.id WHERE client_id = ? AND appointment_admin_status = 'Not paid'";
    public static final String GET_APPOINTMENTS_FOR_MASTER = "SELECT `appointments`.id, `appointments`.start_time, `appointments`.end_time, `appointments`.appointment_master_status, `appointments`.appointment_admin_status, `appointments`.client_id, (SELECT users.name FROM users WHERE users.id=client_id) AS client, products.id, `products`.name, `products`.price,`products`.duration , `products`.master_id, users.name AS master FROM appointments LEFT JOIN products ON appointments.product_id=products.id LEFT JOIN users ON products.master_id=users.id WHERE master_id = ? AND appointment_master_status = 'Not done' OR appointment_master_status = 'In progress'";
    public static final String GET_APPOINTMENTS_FOR_ADMIN_NOT_PAID = "SELECT `appointments`.id, `appointments`.start_time, `appointments`.end_time, `appointments`.appointment_master_status, `appointments`.appointment_admin_status, `appointments`.client_id, (SELECT users.name FROM users WHERE users.id=client_id) AS client, products.id, `products`.name, `products`.price,`products`.duration , `products`.master_id, users.name AS master FROM appointments LEFT JOIN products ON appointments.product_id=products.id LEFT JOIN users ON products.master_id=users.id WHERE appointment_admin_status = 'Not paid' AND appointment_master_status = 'Done'";
    public static final String GET_APPOINTMENTS_FOR_ADMIN_NOT_DONE = "SELECT `appointments`.id, `appointments`.start_time, `appointments`.end_time, `appointments`.appointment_master_status, `appointments`.appointment_admin_status, `appointments`.client_id, (SELECT users.name FROM users WHERE users.id=client_id) AS client, products.id, `products`.name, `products`.price,`products`.duration , `products`.master_id, users.name AS master FROM appointments LEFT JOIN products ON appointments.product_id=products.id LEFT JOIN users ON products.master_id=users.id WHERE appointment_master_status = 'Not done'";
    public static final String UPDATE_DONE_STATUS = "UPDATE `appointments` SET `appointment_master_status` = ? WHERE appointments.id = ?";
    public static final String UPDATE_PAID_STATUS = "UPDATE `appointments` SET `appointment_admin_status` = ? WHERE appointments.id = ?";
    public static final String DELETE_APPOINTMENT = "DELETE FROM appointments WHERE appointments.id = ?";
    public static final String GET_APPOINTMENTS_BY_MASTER_FOR_ADMIN_UPDATE = "SELECT `appointments`.id, `appointments`.start_time, `appointments`.end_time, `appointments`.appointment_master_status, `appointments`.appointment_admin_status, `appointments`.client_id, (SELECT users.name FROM users WHERE users.id=client_id) AS client, products.id, `products`.name, `products`.price,`products`.duration , `products`.master_id, users.name AS master FROM appointments LEFT JOIN products ON appointments.product_id=products.id LEFT JOIN users ON products.master_id=users.id WHERE master_id = ? AND appointments.id != ? AND appointment_master_status = 'Not done' OR appointment_master_status = 'In progress'";
    public static final String UPDATE_APPOINTMENT_TIME = "UPDATE `appointments` SET `start_time` = ?, `end_time` = ? WHERE appointments.id = ?";
    public static final String GET_USERS_EMAIL_FOR_SEND_MESSAGE = "SELECT appointments.end_time, users.email FROM appointments LEFT JOIN users ON appointments.client_id = users.id WHERE DATE (end_time) = ?";
    public static final String GET_WORKING_HOURS = "SELECT * FROM working_hours";
    public static final String SET_WORKING_HOURS = "UPDATE `working_hours` SET start_working_day = ?, end_working_day = ? WHERE id = 1";

}
