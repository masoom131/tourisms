package com.web.tourism.util;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class WebTourismUtil {

    public String setJsonResponse(boolean status, JSONObject result, String message_en) {
        JSONObject jsonResponse = new JSONObject();
        if (status) {
            jsonResponse.put("status", true);
            jsonResponse.put("successMessage", message_en);
            if(Objects.isNull(result)) {
                jsonResponse.put("result", "{}");
            } else {
                jsonResponse.put("result", result);
            }
        } else {
            jsonResponse.put("status", false);
            jsonResponse.put("errorMessage", message_en);
        }
        return jsonResponse.toString();
    }

    //To be called in controller
    public ResponseEntity<String> responseStatus(String registeredUser){
        JSONObject jsonResponse = new JSONObject(registeredUser);
        if (jsonResponse.getBoolean("status")) {
            return new ResponseEntity<>(registeredUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(registeredUser,HttpStatus.FORBIDDEN);
        }
    }
}
