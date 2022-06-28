package utils;

import com.google.gson.JsonObject;
import org.json.JSONObject;

public class APIPayloadConstants {
    public static String createEmployeePayload() {
        String createEmployee = "{\n" +
                "    \"emp_firstname\": \"Mary\",\n" +
                "    \"emp_lastname\": \"Russell\",\n" +
                "    \"emp_middle_name\": \"B\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"2006-06-06\",\n" +
                "    \"emp_status\": \"Active\",\n" +
                "    \"emp_job_title\": \"Developer\"\n" +
                "}";
        return createEmployee;
    }

    public static String createEmployeePayloadViaJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emp_firstname", "Rick");
        jsonObject.put("emp_lastname", "Morty");
        jsonObject.put("emp_middle_name", "Ivanovich");
        jsonObject.put("emp_gender", "M");
        jsonObject.put("emp_birthday", "1994-01-01");
        jsonObject.put("emp_status", "Retired");
        jsonObject.put("emp_job_title", "Scientist");
        return jsonObject.toString();
    }

    public static String createEmployeeDynamicPayload(String firstName, String lastname, String middleName, String gender, String dob, String status, String jobTitle) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("emp_firstname", firstName);
        jsonObject.put("emp_lastname", lastname);
        jsonObject.put("emp_middle_name", middleName);
        jsonObject.put("emp_gender", gender);
        jsonObject.put("emp_birthday", dob);
        jsonObject.put("emp_status", status);
        jsonObject.put("emp_job_title", jobTitle);
        return jsonObject.toString();
    }
}

