package webLogic.controllers;

import dao.UserDAO;
import org.springframework.web.bind.annotation.*;
import webLogic.Json2Object.ActionConstant;
import webLogic.Json2Object.response.ResponseAuthorization;
import model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

@Controller
public class AuthorizationController {

    @RequestMapping(value = "/authorization", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseAuthorization authorization(@RequestBody User reqUser){ //@RequestBody User reqUser

        User respUser = UserDAO.getUser(reqUser.getLogin());
        if (respUser != null)
            return new ResponseAuthorization(ActionConstant.STATUS_SUCCESS, respUser.getLogin(), respUser.getAvatarID());
        else return new ResponseAuthorization(ActionConstant.STATUS_ERROR, "Пользователь не найден.");

    }
}
