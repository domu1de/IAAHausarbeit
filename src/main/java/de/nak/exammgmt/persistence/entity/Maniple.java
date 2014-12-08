/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Maniple extends AbstractEntity {

    private Year year;
    private FieldOfStudy fieldOfStudy;

    @Column(nullable = false)
    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @ManyToOne(optional = false)
    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    @Transient
    public String getAbbreviation() {
        return fieldOfStudy.getAbbreviation() + year.format(DateTimeFormatter.ofPattern("yy"));
    }

    @Override
    public String toString() {
        return fieldOfStudy.getName() + " " + year.format(DateTimeFormatter.ofPattern(("yyyy")));
    }
}
