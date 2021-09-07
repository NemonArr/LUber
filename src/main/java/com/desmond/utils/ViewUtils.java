package main.java.com.desmond.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Clase utilitaria que contiene metodos para controlar los mensajes de error y exito requeridos para ciertas acciones
 */

public class ViewUtils {

    public static void showApplicationErrorMessage(Component parent) {
        JOptionPane.showMessageDialog(parent,
                "Error interno de la aplicación, inténtelo más tarde",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showApplicationChangeSusses(Component parent) {
        JOptionPane.showMessageDialog(parent,
                "Actualizacion exitosa!",
                "ACTUALIZACION",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
