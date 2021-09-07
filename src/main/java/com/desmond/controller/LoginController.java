package main.java.com.desmond.controller;

import main.java.com.desmond.dao.UsersDao;
import main.java.com.desmond.utils.ViewUtils;
import main.java.com.desmond.view.Window;
import main.java.com.desmond.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Controla la interfaz grafica LoginView
 */

public class LoginController {

    private LoginView loginView;
    private Window window;
    private UsersDao usersDao;

    public LoginController(LoginView loginView, Window window, UsersDao usersDao) {
        this.loginView = loginView;
        this.window = window;
        this.usersDao = usersDao;
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String usu = loginView.getUserTextField().getText();
                String pas = loginView.getPasswordTextField().getText();
                try {
                    if(usersDao.login(usu, pas)) {
                        window.showTabs();
                    } else {
                        JOptionPane.showMessageDialog(loginView,
                                "Usuario o contraseña inválidos",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    ViewUtils.showApplicationErrorMessage(loginView);
                }
            }
        };
        loginView.getLoginButton().addActionListener(x);
    }

}
