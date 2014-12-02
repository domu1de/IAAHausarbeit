/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class Course extends AbstractEntity{

    private String title;
    private String description;
    private Maniple maniple;

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public Maniple getManiple() {
        return maniple;
    }

    public void setManiple(Maniple maniple) {
        this.maniple = maniple;
    }
}
