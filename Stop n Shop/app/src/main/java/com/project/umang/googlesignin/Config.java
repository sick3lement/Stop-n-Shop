package com.project.umang.googlesignin;

/**
 * Created by UMANG on 5/27/2017.
 */

public class Config {
    public static final String URL_SIGN_UP="https://exceptional-trim.000webhostapp.com/Ecommerce/register.php";
    public static final String URL_GOOGLE="https://exceptional-trim.000webhostapp.com/Ecommerce/googlesignin.php";
    public static final String URL_GETMEN="https://exceptional-trim.000webhostapp.com/Ecommerce/getAllMen.php";
    public static final String URL_GETWOMEN="https://exceptional-trim.000webhostapp.com/Ecommerce/getAllWomen.php";
    public static final String URL_GETCHILD="https://exceptional-trim.000webhostapp.com/Ecommerce/getAllChildren.php";
    public static final String CART="https://exceptional-trim.000webhostapp.com/Ecommerce/cart.php";
    public static final String FAVOURITES="https://exceptional-trim.000webhostapp.com/Ecommerce/favourites.php";
    public static final String URL_GETCART="https://exceptional-trim.000webhostapp.com/Ecommerce/getAllCart.php";
    public static final String URL_GETFAVOURITES="https://exceptional-trim.000webhostapp.com/Ecommerce/getAllFavourites.php";
    public static final String REMOVECART="https://exceptional-trim.000webhostapp.com/Ecommerce/deletecart.php";
    public static final String REMOVEFAVOURITES="https://exceptional-trim.000webhostapp.com/Ecommerce/deleteFavourites.php";
    public static final String URL_QUANTITY="https://exceptional-trim.000webhostapp.com/Ecommerce/quantity.php";


    public static final String URL_GET_ALL = "https://exceptional-trim.000webhostapp.com/Android/getAllEmp.php";
    public static final String URL_GET_EMP = "https://exceptional-trim.000webhostapp.com/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "https://exceptional-trim.000webhostapp.com/updateEmp.php";
    public static final String URL_DELETE_EMP = "https://exceptional-trim.000webhostapp.com/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_PASSWORD = "password";
    public static final String KEY_EMP_IMAGE= "image";
    public static final String TAG_JSON_ARRAY="result";
    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
    public static final String LOGIN_URL = "https://exceptional-trim.000webhostapp.com/Ecommerce/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
