package webLogic.controllers;


import dao.UserDAO;
import model.User;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseAuthorization;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webLogic.Json2Object.response.ResponseRegistration;

import java.sql.SQLException;


@Controller
public class RegistrationController {


    @RequestMapping(value = "/registration",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseRegistration registration(@RequestBody User reqUser){

        try {
            if (UserDAO.getUser(reqUser.getLogin()) == null) {
                UserDAO.addUser(reqUser);
                return new ResponseRegistration(ActionConstant.STATUS_SUCCESS);
            }
            else return new ResponseRegistration(ActionConstant.STATUS_ERROR,"Такой пользователь уже зарегистрован.");

        } catch (SQLException e) {
            return new ResponseRegistration(ActionConstant.STATUS_ERROR,"DB error");
        }
    }
}
