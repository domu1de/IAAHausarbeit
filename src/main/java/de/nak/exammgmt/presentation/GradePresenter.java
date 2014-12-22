/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation;

/**
 * The GradePresenter is responsible for returning different grade representations.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class GradePresenter {

    /**
     * Return the css class for a given grade.
     *
     * @param grade the grade to get the css class for
     * @return the css class
     */
    public static String toCssClass(float grade) {
        String cssClass = "grade-";

        if (grade < 1.7) {
            cssClass += "a";
        } else if (grade < 2.7) {
            cssClass += "b";
        } else if (grade < 3.7) {
            cssClass += "c";
        } else if (grade <= 4.0) {
            cssClass += "d";
        } else {
            cssClass += "f";
        }

        return cssClass;
    }

}
