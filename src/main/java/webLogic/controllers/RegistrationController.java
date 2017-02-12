package webLogic.controllers;


import dao.UserDAO;
import model.User;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseUser;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseUser registration(@RequestBody User reqUser){

        try {
            if (UserDAO.getUser(reqUser.getLogin()) == null) {
                UserDAO.addUser(reqUser);
                return new ResponseUser(reqUser,reqUser.getHashCode(),ActionConstant.STATUS_SUCCESS);
            }
            else return new ResponseUser(reqUser, ActionConstant.STATUS_ERROR,"Такой пользователь уже зарегистрован.");

        } catch (SQLException e) {
            return new ResponseUser(reqUser,ActionConstant.STATUS_ERROR,"DB error");
            //e.printStackTrace();
        }
    }
}