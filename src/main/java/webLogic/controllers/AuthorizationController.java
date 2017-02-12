package webLogic.controllers;

import dao.UserDAO;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseUser;
import model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class AuthorizationController {

    @RequestMapping(value = "/authorization", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseUser authorization(@RequestBody User reqUser){

        try {
            User respUser = UserDAO.getUser(reqUser.getLogin());
            if (respUser != null) return new ResponseUser(respUser,respUser.hashCode(),ActionConstant.STATUS_SUCCESS);
            else return new ResponseUser(reqUser,ActionConstant.STATUS_ERROR,"Пользователь не найден.");
        } catch (SQLException e) {
            return new ResponseUser(reqUser,ActionConstant.STATUS_ERROR,"DB error");
        }
    }
}
