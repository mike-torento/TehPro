package webLogic.controllers;

import dao.UserDAO;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseAuthorization;
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
    ResponseAuthorization authorization(@RequestBody User reqUser){

        try {
            User respUser = UserDAO.getUser(reqUser.getLogin());
            if (respUser != null) return new ResponseAuthorization(ActionConstant.STATUS_SUCCESS,respUser.getLogin(),respUser.getAvatarID());
            else return new ResponseAuthorization(ActionConstant.STATUS_ERROR,"Пользователь не найден.");
        } catch (SQLException e) {
            return new ResponseAuthorization(ActionConstant.STATUS_ERROR,"DB error");
        }
    }
}
