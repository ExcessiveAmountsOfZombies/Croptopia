package me.thonk.common;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class QuickGenerator {

    // generator class for a few aspects of new items so we don't mess up typing between platforms.
    // super lazy

    public static void main(String[] args) {
        Class<?> clazz = ItemNames.class;

        try {
            createLangEntries(clazz);
            ArrayList<String> variableNames = createForgeItemVariable(clazz);
            createForgeItemRegister(clazz, variableNames);
            createFabricItemVariable(variableNames);
            createFabrcItemRegister(clazz, variableNames);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static void createLangEntries(Class<?> clazz) throws IllegalAccessException {
        System.out.println("Lang Begin");
        for (Field field : getFields(clazz)) {
            String fieldValue = (String) field.get(ItemNames.class);
            String firstCharCapitalized = fieldValue.substring(0, 1).toUpperCase() + fieldValue.substring(1);
            System.out.println(" \"item.croptopia." + fieldValue + "\": \"" + firstCharCapitalized + "\", ");
        }
        System.out.println("Lang Eng");
    }

    private static ArrayList<String> createForgeItemVariable(Class<?> clazz) throws IllegalAccessException {
        // few examples of what we want to make
        // public static Item nuttyCookie;  public static Item praline;
        System.out.println("Forge Var Begin");
        ArrayList<String> variableNames = new ArrayList<>();
        for (Field field : getFields(clazz)) {
            String psi = "public static Item";
            String fieldValue = getFieldValue(field, ItemNames.class);
            char[] array = fieldValue.toCharArray();
            char[] newArray = new char[array.length];
            for (int i = 0; i < fieldValue.toCharArray().length; i++) {
                char c = array[i];
                if (c == '_') {
                    newArray[i] = Character.toUpperCase(array[i + 1]);
                    i++;
                } else {
                    newArray[i] = array[i];
                }
            }
            String varName = new String(newArray);
            variableNames.add(varName);
            String result = psi + " " + new String(newArray) + ";";
            System.out.println(result);
        }
        System.out.println("Forge Var End");
        return variableNames;
    }

    private static void createForgeItemRegister(Class<?> clazz, ArrayList<String> varNames) {
        System.out.println("Forge Item Reg Begin");
        Field[] fields = getFields(clazz);
        for (int i = 0; i < varNames.size(); i++) {
            Field field = fields[i];
            String varName = varNames.get(i);
            System.out.println(varName + " = registerItem(itemRegister, ItemNames." + field.getName() + ", new Item(createGroup()));");
        }
        System.out.println("Forge Item Reg End");
    }

    private static void createFabricItemVariable(ArrayList<String> varNames) {
        System.out.println("Fab Item Var Begin");
        String psi = "public static Item";
        for (String varName : varNames) {
            System.out.println(psi + " " + varName + " = new Item(createGroup());");
        }
        System.out.println("Fab Item Var End");
    }

    private static void createFabrcItemRegister(Class<?> clazz, ArrayList<String> varNames) {
        System.out.println("Fab Item Reg Begin");
        Field[] fields = getFields(clazz);
        for (int i = 0; i < varNames.size(); i++) {
            Field field = fields[i];
            System.out.println("registerItem(ItemNames." + field.getName() + ", " + varNames.get(i) + ");");
        }
        System.out.println("Fab Item Reg End");
    }




    private static Field[] getFields(Class<?> clazz) {
        return clazz.getFields();
    }

    private static String getFieldValue(Field field, Class<?> clazz) throws IllegalAccessException {
        return (String) field.get(clazz);
    }

}
