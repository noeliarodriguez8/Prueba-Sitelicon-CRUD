package com.example.siteliconPrueba;

import java.security.Timestamp;
import java.util.List;

record NewContact(String name, List<String> reason, String email, String message, Timestamp timestamp) {

}
