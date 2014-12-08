/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.model;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
public enum Grade {

    A(1.0f, "txt.grade.A"),
    A_MINUS(1.3f, "txt.grade.A"),
    B_PLUS(1.7f, "txt.grade.B"),
    B(2.0f, "txt.grade.B"),
    B_MINUS(2.3f, "txt.grade.B"),
    C_PLUS(2.7f, "txt.grade.C"),
    C(3.0f, "txt.grade.C"),
    C_MINUS(3.3f, "txt.grade.C"),
    D_PLUS(3.7f, "txt.grade.D"),
    D(4.0f, "txt.grade.D"),
    F_PLUS(5f, "txt.grade.F"),
    F(6f, "txt.grade.F");

    private float value;
    private String key;

    Grade(float value, String key) {
        this.value = value;
        this.key = key;
    }

    public float getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
