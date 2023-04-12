package com.study.basicboard.domain.enum_class;

public enum BoardCategory {
    FREE, GREETING, GOLD;

    public static BoardCategory of(String category) {
        if (category.equalsIgnoreCase("free")) return BoardCategory.FREE;
        else if (category.equalsIgnoreCase("greeting")) return BoardCategory.GREETING;
        else if (category.equalsIgnoreCase("gold")) return BoardCategory.GOLD;
        else return null;
    }
}
