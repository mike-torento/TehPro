package webLogic.controllers;


import model.User;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseAuthorization;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RegistrationController {


    @RequestMapping(value = "/registration",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseAuthorization registration(@RequestBody User reqUser){
//
//        try {
//            if (UserDAO.getUser(reqUser.getLogin()) == null) {
//                UserDAO.addUser(reqUser);
//                return new ResponseUser(reqUser,123,ActionConstant.STATUS_SUCCESS);
//            }
//            else return new ResponseUser(reqUser, ActionConstant.STATUS_ERROR,"Такой пользователь уже зарегистрован.");
//
//        } catch (SQLException e) {
//            return new ResponseUser(reqUser,ActionConstant.STATUS_ERROR,"DB error");
//            //e.printStackTrace();
//        }
        return new ResponseAuthorization(ActionConstant.STATUS_SUCCESS);
    }
}
