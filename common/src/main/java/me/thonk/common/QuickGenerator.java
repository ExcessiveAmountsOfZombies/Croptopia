package me.thonk.common;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class QuickGenerator {

    // generator class for a few aspects of new items so we don't mess up typing between platforms.
    // super lazy

    public static void main(String[] args) {

        try {
            createLangEntries();
            ArrayList<String> variableNames = createForgeItemVariable();
            createForgeItemRegister(variableNames);
            createFabricItemVariable(variableNames);
            createFabrcItemRegister(variableNames);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static void createLangEntries() throws IllegalAccessException {
        for (Field field : getFields()) {
            String fieldValue = (String) field.get(ItemNames.class);
            String firstCharCapitalized = fieldValue.substring(0, 1).toUpperCase() + fieldValue.substring(1);
            System.out.println(" \"item.croptopia." + fieldValue + "\": \"" + firstCharCapitalized + "\", ");
        }
    }

    private static ArrayList<String> createForgeItemVariable() throws IllegalAccessException {
        // few examples of what we want to make
        // public static Item nuttyCookie;  public static Item praline;
        ArrayList<String> variableNames = new ArrayList<>();
        for (Field field : getFields()) {
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
        return variableNames;
    }

    private static void createForgeItemRegister(ArrayList<String> varNames) {
        Field[] fields = getFields();
        for (int i = 0; i < varNames.size(); i++) {
            Field field = fields[i];
            String varName = varNames.get(i);
            System.out.println(varName + " = registerItem(itemRegister, ItemNames." + field.getName() + ", new Item(createGroup()));");
        }
    }

    private static void createFabricItemVariable(ArrayList<String> varNames) {
        String psi = "public static Item";
        for (String varName : varNames) {
            System.out.println(psi + " " + varName + " = new Item(createGroup());");
        }

    }

    private static void createFabrcItemRegister(ArrayList<String> varNames) {
        Field[] fields = getFields();
        for (int i = 0; i < varNames.size(); i++) {
            Field field = fields[i];
            System.out.println("registerItem(ItemNames." + field.getName() + ", " + varNames.get(i) + ");");
        }
    }




    private static Field[] getFields() {
        return ItemNames.class.getFields();
    }

    private static String getFieldValue(Field field, Class<?> clazz) throws IllegalAccessException {
        return (String) field.get(clazz);
    }

}
